package com.example.springspringboottests.controllers;

import com.example.springspringboottests.services.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelloWorldControllerTest {

    private MockMvc mockMvc; // тестирование относящийся к servlet логики

    @Mock // 1. его нужно будет обучить 2. Далее он пойдет в helloWorldController как мок
    private HelloService helloService;

    @InjectMocks
    private HelloWorldController helloWorldController;

    @Before
    public void setUp() throws Exception {
        // создаем mock нашего контроллера
        mockMvc = MockMvcBuilders
                .standaloneSetup(helloWorldController)
                .build();
    }

    /*
    Тестирование контроллера обработки GET запроса
     */
    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/hello") // если тестим POST запрос, то выбираем post()
                )
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200 ok что возвращает
                .andExpect(MockMvcResultMatchers.content().string("hello")); // возвращает какой контент. В нашем случае string и слово hello
    }

    /*
    Тестирование метода, который вызывает сервис
     */
    @Test
    public void testHelloFromService() throws Exception {

        Mockito.when(helloService.hello()).thenReturn("Hello world"); // 1. обучаем mock

        mockMvc.perform(
                MockMvcRequestBuilders.get("/hello/db") // 2. Вызывается endpoint и наш обученный мок его заменяет
        )
                .andExpect(MockMvcResultMatchers.status().isOk()) // 200 ok что возвращает
                .andExpect(MockMvcResultMatchers.content().string("Hello world"));

        /*
        если мы используем when, тоесть обучаем, обязательно использование verify()
         */
        Mockito.verify(helloService).hello(); // подтверждение что данный сервис был вызван
    }

}