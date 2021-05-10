package com.demo3.demo3.category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryClassTests {
    @Test
    public void testGetName(){
        Category category = new Category("test");
        String test = category.getName();
        assertTrue(test.equals("test"));
    }

    @Test
    public void testSetName(){
        Category category = new Category();
        category.setName("test");
        String test = category.getName();
        assertTrue(test.equals("test"));
    }
}
