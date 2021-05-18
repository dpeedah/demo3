package com.demo3.demo3.category;

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
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private static Long id;


    @Test
    public void testGetCategoryAllValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/categories/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCategoryByIdValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/categories/byid/"+1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetCategoryByIdInvalid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/categories/byid/"+0.1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @BeforeAll
    public void testCreateValidCategory() throws Exception
    {
        //code to randomly generate the name
        Random rand = new Random();
        String randomString = "abc";
        for(int i=0;i<10;i++){
            char c = (char)(rand.nextInt(26) + 'a');
            randomString = randomString + String.valueOf(c);
        }

        Category category = new Category("random"+randomString);
        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/api/categories/create")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        int newid = JsonPath.read(result.getResponse().getContentAsString(), "$.categoryId");
        this.id = Integer.toUnsignedLong(newid);

        assertNotNull(this.id);

    }

    @Test
    public void testCreateInvalidCategory() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/categories/create")
                .content(asJsonString(new Category("123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateEmptyCategory() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/categories/create")
                .content(asJsonString(new Category(" ")))
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
                .delete("/api/categories/{id}",this.id))
                .andExpect(status().isAccepted());
    }
}
