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

import java.math.BigDecimal;

@SpringBootTest(classes = RestApplication.class)
@WebAppConfiguration
public class RestCallTest {

    @Autowired
    private WebApplicationContext ctx;

    final String BASE_URL = "http://localhost:8080/";

    @Test
    public void testSum() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/sum?a=10&b=5"))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(jsonPath("$.result", is(15)));
    }

    @Test
    public void testSubtract() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/subtract?a=10&b=5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result", is(5)));
    }

    @Test
    public void testMultiply() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/multiply?a=10&b=5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result", is(50)));
    }

    @Test
    public void testDivide() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/divide?a=10&b=5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result", is(2)));
    }

    @Test
    public void testDivideWithRemainder() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/divide?a=10&b=4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result", is(2.5)));
    }

    @Test
    public void testDivideWithRepeatingDigits() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/divide?a=10&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.result", is(new BigDecimal("3.3333333333333333333333333333333333333333333333334"))));
    }

    @Test
    public void testDivideByZero() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc.perform(get("/divide?a=10&b=0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.reasonPhrase", is("Bad Request")))
                .andExpect(jsonPath("$.message", is("ERROR: Cannot divide by ZERO")));
    }
}