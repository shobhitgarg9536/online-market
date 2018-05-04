package com.shobhit.onlineshopping.controller;


import com.shobhit.dao.CategoryDao;
import com.shobhit.dao.ProductDAO;
import com.shobhit.dto.Category;
import com.shobhit.dto.Product;
import com.shobhit.onlineshopping.exception.NoProductFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageController{

    public static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ProductDAO productDAO;

@RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index(){

    logger.info("inside home");
    logger.debug("inside home");
    ModelAndView mv = new ModelAndView("page");
    mv.addObject("title", "Home");
    mv.addObject("categories", categoryDao.list());
    mv.addObject("userClickHome", true);
    return mv;
}

    @RequestMapping(value = "/about")
    public ModelAndView about(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "About Us");
        mv.addObject("userClickAbout", true);
        return mv;
    }

    @RequestMapping(value = "/contact")
    public ModelAndView contact(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Contact Us");
        mv.addObject("userClickContact", true);
        return mv;
    }
    @RequestMapping(value = "/show/all/products")
    public ModelAndView showAllProducts(){
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "All products");
        mv.addObject("categories", categoryDao.list());
        mv.addObject("userClickAllProducts", true);
        return mv;
    }
    @RequestMapping(value = "/show/category/{id}/products")
    public ModelAndView showCategoryProducts(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("page");

        Category category = null;
        category = categoryDao.get(id);
        mv.addObject("title", category.getName());
        mv.addObject("category", category);
        mv.addObject("categories",categoryDao.list());
        mv.addObject("userClickCategoryProducts", true);
        return mv;
    }

 /* Viewing a single product
	 * */

    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable int id) throws NoProductFoundException {

        ModelAndView mv = new ModelAndView("page");

        Product product = productDAO.get(id);

        if(product == null) throw new NoProductFoundException();
        // update the view count
        product.setViews(product.getViews() + 1);
        productDAO.update(product);
        //---------------------------

        mv.addObject("title", product.getName());
        mv.addObject("product", product);

        mv.addObject("userClickShowProduct", true);


        return mv;

    }
    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout){
        ModelAndView mv = new ModelAndView("login");
        if(error!=null)
            mv.addObject("message", "Invalid email or password");
        if(logout!=null)
            mv.addObject("logout", "Successfully logout");

        mv.addObject("title", "Login");
        return mv;
    }

    @RequestMapping(value = "/access-denied")
    public ModelAndView access_denied(){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("title", "403- Access denied");
        mv.addObject("errorTitle", "Unauthorized user");
        mv.addObject("errorDescription", "You are not a authorized user");
        return mv;
    }

    @RequestMapping(value="/perform-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidates HTTP Session, then unbinds any objects bound to it.
        // Removes the authentication from securitycontext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

}