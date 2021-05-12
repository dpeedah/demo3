package com.demo3.demo3.category;

import com.demo3.demo3.actor.Actor;
import com.demo3.demo3.actor.ActorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryRepoTests {
    @Autowired
    private CategoryRepository categoryRepo;

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
    public void testGetId(){
        Optional<Category> category = categoryRepo.findById(1L);
        assertTrue(category.get().getCategoryId().equals(1L));
    }

    @Test
    public void testGetLastUpdate(){
        Optional<Category> category = categoryRepo.findById(1L);
        Date categoryDate = category.get().getLastUpdate();
        Date testDate = Date.from(LocalDateTime.of(2006,02,15,04,46,27,0).toInstant(ZoneOffset.UTC));
        assertEquals(testDate.compareTo(categoryDate),0);
    }

    @Test
    public void findCategoryByName(){
        Optional<Category> category = categoryRepo.findCategoryByName("Action");
        assertEquals("Action",category.get().getName());
    }

    @Test
    public void findCategoryByNameFail(){
        assertThrows(IllegalArgumentException.class,()->{
            Optional<Category> category = categoryRepo.findCategoryByName("Nonexistent");
        });
    }
}
