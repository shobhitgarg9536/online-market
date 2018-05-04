package com.shobhit.onlineshopping.controller;

import com.shobhit.dao.ProductDAO;
import com.shobhit.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/json/data")
public class JsonDataController {

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping(value = "/all/products")
    @ResponseBody
    public List<Product> getAllProducts(){
        return productDAO.listActiveProducts();
    }

    @RequestMapping(value = "/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsList(){
        return productDAO.list();
    }


    @RequestMapping(value = "/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id){
        return productDAO.listActiveProductsByCategory(id);
    }
    @RequestMapping("/mv/products")
    @ResponseBody
    public List<Product> getMostViewedProducts() {
        return productDAO.getProductsByParam("views", 5);
    }

    @RequestMapping("/mp/products")
    @ResponseBody
    public List<Product> getMostPurchasedProducts() {
        return productDAO.getProductsByParam("purchases", 5);
    }


}