package com.demo3.demo3.film;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    private static Long id;
    private Long randomisedKey = ThreadLocalRandom.current().nextLong(1200, 72000);
    private String randomisedKeyStr = String.valueOf(randomisedKey);


    public String asJsonString(final Object obj) {
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
    public void test_a_createFilmValid() throws Exception
    {
        Film a = new Film("TESTINGGG" + randomisedKeyStr, "A TEST DESC", 2005L, 105L);

        MvcResult result= mockMvc.perform( MockMvcRequestBuilders
                .post("/api/films/create")
                .content(asJsonString(a))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("TESTINGGG"+randomisedKeyStr))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A TEST DESC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value(2005))
                .andReturn();

                int newid = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
                this.id = Integer.toUnsignedLong(newid);

                assertNotNull(this.id);


    }

    @Test
    public void test_b_updateFilmValid() throws Exception
    {
        String updateUrl = "/api/films/update/{id}";
        Film a = new Film("TESTING HAS UPDATED" + randomisedKeyStr, "A TEST DESC 2", 2012L, 105L);
        int id = this.id.intValue();
        MvcResult result= mockMvc.perform( MockMvcRequestBuilders
                .put(updateUrl,id)
                .content(asJsonString(a))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("TESTING HAS UPDATED"+randomisedKeyStr))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A TEST DESC 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear").value(2012))
                .andReturn();

        assertNotNull(this.id);


    }

    @Test
    public void createFilmNull() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/films/create")
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

    /*Test
    public void getFilmsByYearValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/films/exists/release/{year}",2005L))
                .andExpect(status().isAccepted());
    }*/

    @Test
    public void test_z_deleteFilm() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/api/films/{id}", this.id) )
                .andExpect(status().isAccepted());
    }
}
