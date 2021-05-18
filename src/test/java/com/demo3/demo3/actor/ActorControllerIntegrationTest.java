package com.demo3.demo3.actor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static Long id;

    @Test
    public void testGetActorAllValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetActorByIdValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/byid/"+1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetActorByIdInvalid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/byid/"+0.1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BeforeAll
    public void testCreateValidActor() throws Exception
    {
        //code to randomly generate the name
        Random rand = new Random();
        String randomString = "abc";
        for(int i=0;i<10;i++){
            char c = (char)(rand.nextInt(26) + 'a');
            randomString = randomString + String.valueOf(c);
        }

        Actor actor = new Actor("random"+randomString,"grgveg");
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(actor))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        int newid = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        this.id = Integer.toUnsignedLong(newid);

        assertNotNull(this.id);
    }

    @Test
    public void testCreateInvalidActor() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(new Actor("123","123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateEmptyActors() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(new Actor(" "," ")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @AfterAll
    public void testDeleteCategory() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/api/actors/{id}",this.id))
                .andExpect(status().isAccepted());
    }
}
