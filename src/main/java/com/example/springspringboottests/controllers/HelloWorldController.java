package com.example.springspringboottests.controllers;

import com.example.springspringboottests.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    private HelloService helloService;

    @Autowired
    public HelloWorldController(HelloService helloService) {
        this.helloService = helloService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path =""
    )
    public String helloWorld(){
        return "hello";
    }

    /*
    Пример работы запрса от сервиса, который например получает данные из базы данных.
     */
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path ="/db"
    )
    public String helloFromService(){
        return helloService.hello();
    }





}
