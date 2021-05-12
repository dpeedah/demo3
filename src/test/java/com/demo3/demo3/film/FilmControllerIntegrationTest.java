package com.demo3.demo3.film;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    private Long id = null;
    private Long randomisedKey = ThreadLocalRandom.current().nextLong(1200, 72000);
    private String randomisedKeyStr = String.valueOf(randomisedKey);


    @Test
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void testFilmGetAllValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/films/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createFilmValid() throws Exception
    {
        Film a = new Film("TESTING" + randomisedKeyStr, "A TEST DESC", 2005L, 105L);

        MvcResult result= mockMvc.perform( MockMvcRequestBuilders
                .post("/api/films/create")
                .content(asJsonString(a))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("TESTING"+randomisedKeyStr))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A TEST DESC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value(2005))
                .andReturn();

                int newid = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                this.id = new Long(newid);

                assertTrue(id != null);

    }

    @Test
    public void createFilmNull() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/api/films/create")
                .content(asJsonString(new Film()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getFilmInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/{id}",-2L))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getFilmValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/{id}",20L))
                .andExpect(status().isAccepted()).andExpect(MockMvcResultMatchers.jsonPath(("$.id")).value(20L));
    }

    @Test
    public void deleteFilm() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/api/films/{id}", this.id) )
                .andExpect(status().isAccepted());
    }
}
