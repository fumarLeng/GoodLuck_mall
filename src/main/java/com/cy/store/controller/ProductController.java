package com.cy.store.controller;

import com.cy.store.entity.Product;
import com.cy.store.service.IProductService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("products")
@RestController
public class ProductController extends BassController {
    @Autowired
    private IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList() {

        List<Product> data = productService.findHostList();
        return new JsonResult<List<Product>>(OK, data);
    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        Product data = productService.findById(id);
        return new JsonResult<Product>(OK, data);
    }




}
