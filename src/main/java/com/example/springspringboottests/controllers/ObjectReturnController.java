package com.example.springspringboottests.controllers;

import com.example.springspringboottests.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class ObjectReturnController {

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = ""
    )
    public Student returnStudentInfo(){
        return new Student("Jason","Statham");
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, // для упрощения. Отправить тоже самое что получили
            path = "/send"
    )
    public Student returnStudentInfo2(@RequestBody Student student){
        return student;
    }

}
