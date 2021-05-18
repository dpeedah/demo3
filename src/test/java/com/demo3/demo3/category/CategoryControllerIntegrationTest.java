package com.demo3.demo3.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

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
    public void testCreateValidCategory() throws Exception
    {
        Category category = new Category("kjerlkbf");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/categories/create")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").exists());

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
    public void testDeleteCategory() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/api/categories/",7))
                .andExpect(status().isAccepted());
    }
}
