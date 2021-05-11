package com.demo3.demo3.actor;

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
class ActorClassTests {

    @Autowired
    private ActorRepository actorRepo;

    private static Actor actor;
    private static final String firstName = "Harry";
    private static final String lastName = "Phillips";

    //before testing starts make the actor
    @BeforeAll
    static void beforeAllTests(){
        actor = new Actor(firstName,lastName);
    }

    //before each test set the name back to default incase previous tests changed it
    @BeforeEach
    void beforeEachTest(){
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
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
        assertEquals(firstName,actor.getFirstName());
    }

    @Test
    public void testGetLastName(){
        assertEquals(lastName,actor.getLastName());
    }

    @Test
    public void testSetFirstName() {
        actor.setFirstName("Changed");
        assertTrue(actor.getFirstName().equals("Changed"));
    }

    @Test
    public void testSetLastName() {
        actor.setLastName("Changed");
        assertTrue(actor.getLastName().equals("Changed"));
    }
}
