package com.learntotestviajunit5.controllers;

public class IndexController {

    public String index(){
        return "index";
    }

    public String oupsHandler(){

        return "notimplemented";
    }

    public String oupsHandlerWithExcpetion() {
        throw new RuntimeException();
      }
}
