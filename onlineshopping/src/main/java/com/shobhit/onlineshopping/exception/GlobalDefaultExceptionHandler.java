package com.shobhit.onlineshopping.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView NoHandlerFoundException(){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle","The page is under construction!");
        mv.addObject("errorDescription","The page you are looking for is not available.");
        mv.addObject("title","404 Error");
        return mv;
    }

    @ExceptionHandler(NoProductFoundException.class)
    public ModelAndView NoProductFoundException(){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle","Product not available!");
        mv.addObject("errorDescription","The product you are looking for is not available right now.");
        mv.addObject("title","Product not available");
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex) {

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorTitle", "Contact Your Administrator!!");

        /* only for debugging your application*/
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        ex.printStackTrace(pw);

        mv.addObject("errorDescription", sw.toString());
        mv.addObject("title", "Error");

        return mv;
    }
}
