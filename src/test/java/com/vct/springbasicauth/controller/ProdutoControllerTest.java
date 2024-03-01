package com.vct.springbasicauth.controller;

import com.vct.springbasicauth.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProdutoControllerTest {

    MockMvc mockMvc;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoController produtoController;

    @BeforeEach
    void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.produtoController)
                .defaultRequest(get("/produtos").accept(MediaType.APPLICATION_JSON))
                .alwaysExpect(content().contentType("application/json"))
                .build();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/produtos")).andExpectAll(
                status().isOk(),
                content().contentType("application/json"));
    }

    @Test
    void findOne() throws Exception {
        mockMvc.perform(get("/produtos/{id}", 1)).andExpectAll(
            status().isOk(),
            content().contentType("application/json"));
    }

    @Test
    void addProduct() throws Exception {
        String json = "{\"nome\":\"Iphone 16\",\"preco\":\"2300.0\"}";
        mockMvc.perform(post("/produtos").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}