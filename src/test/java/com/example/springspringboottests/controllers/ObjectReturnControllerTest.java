package com.example.springspringboottests.controllers;



import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
public class ObjectReturnControllerTest {

    private MockMvc mockMvc; // тестирование относящийся к servlet логики

    @InjectMocks
    private ObjectReturnController objectReturnController;

    @Before
    public void setUp() throws Exception {
        // создаем mock нашего контроллера
        mockMvc = MockMvcBuilders
                .standaloneSetup(objectReturnController)
                .build();
    }

    @Test
    public void testReturnStudentInfo() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
                                      .accept(MediaType.APPLICATION_JSON) // только указанный тип принимает этот endpoint
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Jason")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Born")));
    }

    @Test
    public void testReturnStudentInfo2() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/students")
                        .accept(MediaType.APPLICATION_JSON) // только указанный тип принимает этот endpoint
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2))); // когда мы просто знаем, что два поля передадутся
    }

    /*
    Тестрование Post запроса
     */
    @Test
    public void testReturnStudentInfo3() throws Exception {
        String json = "{\n" +
                "  \"name\": \"John\",\n" +
                "  \"surname\": \"Week\"\n" +
                "}";
        mockMvc.perform(
                MockMvcRequestBuilders  .post("/students/send")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Week")));
    }

}