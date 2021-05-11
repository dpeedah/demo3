package com.demo3.demo3.actor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActorClassTests {

    @Autowired
    private ActorRepository actorRepo;

    static Actor actor;

    //before testing starts make the actor
    @BeforeAll
    static void pretest(){
        actor = new Actor("first","last");
    }

    //before each test set the name back to default incase previous tests changed it
    @BeforeEach
    void pretest2(){
        actor.setFirstName("first");
        actor.setLastName("last");
    }

    @Test
    public void testGetId(){
        Optional<Actor> actor = actorRepo.findById(1L);
        assertTrue(actor.get().getId().equals(1L));
    }

    @Test
    public void testGetLastUpdate(){
        Optional<Actor> actor = actorRepo.findById(1L);
        Date actorsDate = actor.get().getLastUpdate();
        Date testDate = Date.from(LocalDateTime.of(2006,02,15,04,34,33,0).toInstant(ZoneOffset.UTC));
        assertEquals(testDate.compareTo(actorsDate),0);
    }

    @Test
    public void testGetFirstName(){
        assertEquals("first",actor.getFirstName());
    }

    @Test
    public void testGetLastName(){
        assertEquals("last",actor.getLastName());
    }

    @Test
    public void testSetFirstName() {
        actor.setFirstName("test");
        assertTrue(actor.getFirstName().equals("test"));
    }

    @Test
    public void testSetLastName() {
        actor.setLastName("test");
        assertTrue(actor.getLastName().equals("test"));
    }
}
//test