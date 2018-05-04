package com.shobhit.onlineshopping.controller;

import com.shobhit.dao.CategoryDao;
import com.shobhit.dao.ProductDAO;
import com.shobhit.dto.Category;
import com.shobhit.dto.Product;
import com.shobhit.onlineshopping.util.FileUtil;
import com.shobhit.onlineshopping.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.PrimitiveIterator;

@Controller
@RequestMapping("/manage")
public class ManageProduct {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ProductDAO productDAO;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView manageProduct(@RequestParam(name = "success", required = false)String success){
        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title","Product Management");
        mv.addObject("userClickManageProduct",true);

        Product nProduct = new Product();

        nProduct.setActive(true);
        nProduct.setSupplierId(1);

        mv.addObject("product", nProduct);

        if(success != null) {
            if (success.equals("product")) {
                mv.addObject("message", "Successfully added product");
            }
            if (success.equals("category")) {
                mv.addObject("message", "Successfully added category");
            }
        }
        return mv;
    }

    @ModelAttribute("categories")
    public List<Category> getlist(){
        return categoryDao.list();
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String handleManageProduct(@Valid@ModelAttribute("product") Product mProduct, BindingResult result, Model model,
                                      HttpServletRequest request){

        if(mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, result);
        }else {
            if(!mProduct.getFile().getOriginalFilename().equals(""))
                new ProductValidator().validate(mProduct, result);
        }

        if(result.hasErrors()){
            model.addAttribute("title","Product Management");
            model.addAttribute("userClickManageProduct",true);
            model.addAttribute("message", "Validation fails for adding the product!");

            return "page";
        }
        if(mProduct.getId() == 0) {
            //create a new product if th the id =0
            productDAO.add(mProduct);
        }else {
            productDAO.update(mProduct);
        }

        //upload the file
        if(!mProduct.getFile().getOriginalFilename().equals("") ){
            FileUtil.uploadFile(request, mProduct.getFile(), mProduct.getCode());
        }

        return "redirect:/manage/product?success=product";
    }

    @RequestMapping("/{id}/product")
    public ModelAndView handleEditProduct(@PathVariable int id){

        ModelAndView mv = new ModelAndView("page");

        mv.addObject("title","Product Management");
        mv.addObject("userClickManageProduct",true);

        Product product = productDAO.get(id);

        mv.addObject("product", product);
        return mv;
    }

    @RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
    @ResponseBody
    public String handleActivationProduct(@PathVariable int id){

        Product product = productDAO.get(id);
        Boolean isActive = product.isActive();

        product.setActive(!product.isActive());
        productDAO.update(product);
        return isActive? "You have successfully deactivate the Product with id"+id :
                "You have successfully activate the Product with id" + id;
    }

    @ModelAttribute("category")
    public Category getCategory(){
        return new Category();
    }

    @RequestMapping(value = "/category",method = RequestMethod.POST)
    public String handleCategory(@ModelAttribute Category category){
        categoryDao.add(category);
        return "redirect:/manage/product?success=category";
    }
}
