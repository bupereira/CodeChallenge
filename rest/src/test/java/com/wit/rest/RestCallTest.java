package com.wit.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = RestApplication.class)
@WebAppConfiguration
public class RestCallTest {

    @Autowired
    private WebApplicationContext ctx;

    final String BASE_URL = "http://localhost:8080/";


    @Test
    public void testRest_Sum_10_5() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/sum?a=10&b=5"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json;charset=UTF-8"))
               .andExpect(jsonPath("$.result", is(15)));
    }
}