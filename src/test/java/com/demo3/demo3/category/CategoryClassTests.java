package com.demo3.demo3.category;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClassTests {

    private static Category category;
    private static final String name = "Anime";

    //before testing starts make the category
    @BeforeAll
    static void beforeAllTests(){
        category = new Category(name);
    }

    //before each test set the name back to default incase previous tests changed it
    @BeforeEach
    void beforeEachTest(){
        category.setName(name);
    }



    @Test
    public void testGetName(){
        assertEquals(name,category.getName());
    }

    @Test
    public void testSetNameValid(){
        category.setName("Changed");
        assertTrue(category.getName().equals("Changed"));
    }

    @Test
    public void testSetNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            category.setName("123");
        });
    }

    @Test
    public void testSetNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            category.setName("");
        });
    }

    @Test
    public void testToString(){
        String expectedValue = "Category{" +
                "category_id=" + category.getCategoryId() +
                ", name='" + name + '\'' +
                '}';
        assertEquals(expectedValue,category.toString());
    }

}
