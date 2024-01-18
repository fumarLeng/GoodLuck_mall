package com.cy.store.service.impl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

        /**
         * 這個方法藉由父代號parent拿到國家跟市的資料,
         * 拿到後資料之後裡面的id跟parent就沒有作用了
         * 所以可以設置null,提升一點效率
         */
        for (District d : list) {
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

//------------------------------------------------------------------------//

    @Override
    public String getNameByCode(String code) {

        return districtMapper.findNameByCode(code);
    }

//------------------------------------------------------------------------//


}
