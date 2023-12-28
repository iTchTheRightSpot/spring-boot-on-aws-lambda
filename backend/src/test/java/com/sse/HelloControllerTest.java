package com.sse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HelloControllerTest {

    @Value(value = "/${api.base.url}")
    private String url;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void persist() throws Exception {
        // create 1
        this.mockMvc
                .perform(post(this.url)
                        .content(mapper.writeValueAsString(new HelloMessage("Jr-level Developer")))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        // create 2
        this.mockMvc
                .perform(post(this.url)
                        .content(mapper.writeValueAsString(new HelloMessage("Mid-level Developer")))
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        // get 1
        this.mockMvc
                .perform(get(this.url + "/{index}", 0))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"name\": \"Jr-level Developer\" }"));

        // get all
        this.mockMvc
                .perform(get(this.url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

}