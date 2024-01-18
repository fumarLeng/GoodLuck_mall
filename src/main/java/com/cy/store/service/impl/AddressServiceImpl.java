package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    //增加會員收貨地址 Service 層依賴 DistrictService 的介面
    @Autowired
    private IDistrictService districtService;

    @Value("20")
    private int maxCount;

    //------------------------------------------------------------------------//
    //新增收貨地址實作
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //找收貨地址計算筆數的方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("會員收貨地址超過上限");
        }

        //再這個方法中把從districtService介面得到的省市區資料傳遞到address物件裡面
        //所以address裡面包含的所有會員收貨地址的資料
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        //uid , isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0; //1 表示預設 0 表示沒有預設
        address.setIsDefault(isDefault);
        //補齊baseEntity裡面的四個VO
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());


        //新增方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("新增用戶地址時出現未知的異常");
        }
    }
//------------------------------------------------------------------------//
//從uid找到地址

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        //更改一下回傳不需要那麼多,可以提升效率,不打不影響程式運作
//        for (Address address : list) {
//            //address.setAid(null);
//            //address.setUid(null);
//            address.setProvinceCode(null);
//            address.setCityCode(null);
//            address.setAreaCode(null);
//            address.setTel(null);
//            address.setIsDefault(null);
//            address.setCreatedTime(null);
//            address.setCreatedUser(null);
//            address.setModifiedTime(null);
//            address.setModifiedUser(null);
//
//        }
        return list;
    }
//---------------------------------------------------------------------------//

    //設定地址是預設
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {

        //先找傳進來的aid存不存在
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收貨地址不存在");
        }
        //檢查得到的aid參數與資料庫對應的uid有沒有對應到方法收到的uid
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("地址資料不匹配");
        }
        //把所有地只收設定成不是默認 is_default=0
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新資料錯誤");
        }
        //把會員點擊默認按鈕送來的地址設定成默認
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新資料異常");
        }
    }

    //---------------------------------------------------------------------------//


    //刪除會員收貨地址
    @Override
    public void delete(Integer aid, Integer uid, String username) {

        //先拿到aid看資料存不存在
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收貨地址不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("地址資料不匹配");
        }

        //執行刪除地址方法
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("刪除地址時發生異常");
        }

        //查詢會員收貨地址數量
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //停止
            return;
        }

        if (result.getIsDefault() == 0) {
            return;
        }

        //把這個方法查詢到的最後修改地址的is_default設定為1 表示預設
        Address address = addressMapper.findLastModified(uid);
        rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新時發生未知的異常");
        }
    }

//------------------------------------------------------------------------//

    //訂單需要透過這個找到商品
    @Override
    public Address getByAid(Integer aid,Integer uid) {
       Address address =  addressMapper.findByAid(aid);
        if(address == null){
            throw  new ProductNotFoundException("商品找不到");
        }
        if(!address.getUid().equals(uid)){
            throw  new AccessDeniedException("資料異常");
        }
        return address;
    }

//------------------------------------------------------------------------//



}
