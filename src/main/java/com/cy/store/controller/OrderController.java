package com.cy.store.controller;


import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BassController {

    @Autowired
    private IOrderService orderService;


    //新增訂單
    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        Order data = orderService.create(aid, uid, username, cids);
        return new JsonResult<>(OK, data);

    }

    //透過uid查詢order oid 再透過oid查詢 order_item裡面的商品
    @RequestMapping("/queryOrderVo")
    public JsonResult<List<OrderVO>> queryOrderVoByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<OrderVO> orderVos = orderService.queryOrderVoByUid(uid);
        return new JsonResult<>(OK, orderVos);
    }

    @RequestMapping("/uidOrderVoStatus")
    public JsonResult<List<OrderVO>>  queryOrderVoStatusByUid(HttpSession session,Integer status){
        Integer uid = getuidFromSession(session);
        List<OrderVO> orderVoStatus = orderService.queryOrderVoStatusByUid(uid,status);

        return new JsonResult<>(OK,orderVoStatus);
    }

}
