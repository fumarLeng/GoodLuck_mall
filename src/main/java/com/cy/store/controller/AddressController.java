package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BassController {
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new JsonResult<>(OK, data);

    }

    // 請求的參數如果一樣是 aid 會對應到方法參數 integer aid
    //不一樣要用 @PathVariable("aid") "aid"裡面放請求的參數名稱
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
                                       HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.setDefault(aid, uid, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,
                                   HttpSession session) {
        addressService.delete(aid,
                getuidFromSession(session),
                getUsernameFromSession(session));
        return new JsonResult<>(OK);

    }

}
