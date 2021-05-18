package com.demo3.demo3.category;

import com.demo3.demo3.Demo3Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Application.class)
public class CategoryClassTest {


    @Test
    public void testGetName(){
        Category category = new Category("name");
        assertEquals("name",category.getName());
    }

    @Test
    public void testSetNameValid(){
        Category category = new Category("name");
        category.setName("Changed");
        assertEquals("Changed",category.getName());
    }

    @Test
    public void testSetNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            Category category = new Category("name");
            category.setName("123");
        });
    }

    @Test
    public void testSetNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            Category category = new Category("name");
            category.setName("");
        });
    }

    @Test
    public void testToString(){
        Category category = new Category("name");
        String expectedValue = "Category{" +
                "category_id=" + category.getCategoryId() +
                ", name='" + category.getName() + '\'' +
                '}';
        assertEquals(expectedValue,category.toString());
    }

}
