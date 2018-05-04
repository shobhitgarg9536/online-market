package com.shobhit.onlineshopping.exception;

public class NoProductFoundException extends Exception {

    String message;


   public NoProductFoundException(){
        message = "No Product Available";
    }

   public NoProductFoundException(String message){
        this.message = System.currentTimeMillis()+": "+message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
