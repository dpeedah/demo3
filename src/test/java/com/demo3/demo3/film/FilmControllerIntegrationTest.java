package com.demo3.demo3.film;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FilmController.class)
public class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    private Long id = null;
    private Long randomisedKey = ThreadLocalRandom.current().nextLong(1200, 72000);
    private String randomisedKeyStr = String.valueOf(randomisedKey);
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
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.film").exists());
    }

    @Test
    public void createFilmValid() throws Exception
    {
        MvcResult result= mockMvc.perform( MockMvcRequestBuilders
                .put("/api/films/create")
                .content(asJsonString(new Film("TESTING" + randomisedKeyStr, "A TEST DESC", 2005L, 105L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("TESTING"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A TEST DESC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value(2005L))
                .andReturn();

                Long id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                this.id = id;

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
    void getFilmInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/{id}",-2L))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getFilmValid() throws Exception {
        String idstr = String.valueOf(this.id);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/{id}",20L))
                .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(("$.id")).value(idstr));
    }

    @Test
    public void deleteEmployeeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/api/films/{id}", this.id) )
                .andExpect(status().isAccepted());
    }
}
