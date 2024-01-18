package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.*;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    //購物車的service層依賴
    //購物車的mapper和商品的mapper
    //把加入購物車當作加入商品,商品id交給商品mapper層去查詢

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;


    //--------------------------------------------------------------------------------//
//加入購物車
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {

        Date date = new Date();

        //先依靠uid,pid查目前要加入的購物車在表格裡面有沒有
        Cart result = cartMapper.fundByUidAndPid(uid, pid);

        if (result == null) { //null表示商品不再購物車中,所以要新增

            Cart cart = new Cart();//new 一個新的購物車,所以要把需要的靠傳進來的參數塞進去

            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //價格要呼叫商品傳過來,商品要先用傳進來的參數pid去取得價格
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("加入購物車時發生異常");

            }
        } else { //表示購物車表格有這個商品,所以更新他的數量num
            Integer num = result.getNum() + amount;
            //這邊沒有cid要靠上面的result裡面拿
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, date);

            if (rows != 1) {
                throw new UpdateException("更新購物車時發生異常");
            }

        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {

        return cartMapper.findVOByUid(uid);
    }

//--------------------------------------------------------------------------------//

    //購物車前端頁面"+"數量
    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        //先拿到cid
        Cart result = cartMapper.findByCid(cid);
        //如果是null會丟異常購物車不存在
        if (result == null) {
            throw new CartNotFoundException("購物車不存在");
        }

        //在查結果的uid跟參數uid有沒有布一樣
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("目前會員id與登錄會員id不一致");
        }

        //根據查到的結果如果有一個的,數量取出來 +1
        Integer num = result.getNum() + 1;

        Date date = new Date();
        //再用cartMapper裡面的updateNumByCid來找到數量更新
        Integer rows = cartMapper.updateNumByCid(cid, num, username, date);
        if (rows != 1) {
            throw new InsertException("修改數量有問題");
        }

        return num;
    }


    //購物車前端頁面"-"數量
    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {

        Cart result = cartMapper.findByCid(cid);

        Integer num = result.getNum() - 1;
        Date date = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, username, date);
        if (rows != 1) {
            throw new InsertException("修改數量有問題");
        }
        return num;
    }
//--------------------------------------------------------------------------------//


    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        Iterator<CartVO> it = list.iterator();
        while(it.hasNext()){
            CartVO cartVO = it.next();
            if(!cartVO.getUid().equals(uid)){
                list.remove(cartVO);
            }
        }
        return list;
    }

//--------------------------------------------------------------------------------//

    //根據cid刪除商品
    @Override
    public int deleteCartByCid(Integer cid) {
        int result = cartMapper.deleteCartByCid(cid);

        if (result == 0){
            throw new DeleteException("刪除時發生問題");
        }
        return result;
    }
//--------------------------------------------------------------------------------//


}
