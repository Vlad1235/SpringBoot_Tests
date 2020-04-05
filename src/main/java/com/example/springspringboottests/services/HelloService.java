package com.example.springspringboottests.services;

import org.springframework.stereotype.Service;

/*
Сервис работы например с базой данных
 */
@Service
public class HelloService {

    /*
    тут типо логика какая-то сложная
     */
    public String hello() {
        return "Hello world";
    }
}
