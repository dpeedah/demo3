package com.demo3.demo3.actor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActorRepoTests {

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
        Optional<Actor> actor = actorRepo.findById(2L);
        Date actorsDate = actor.get().getLastUpdate();
        Date testDate = Date.from(LocalDateTime.of(2006,02,15,04,34,33,0).toInstant(ZoneOffset.UTC));
        assertEquals(testDate.compareTo(actorsDate),0);
    }

    @Test
    public void testFindActorByFirstNameAndLastNameValid(){
        Optional<Actor> actor = actorRepo.findActorByFirstNameAndLastName("NICK","WAHLBERG");
        assertEquals("NICK",actor.get().getFirstName());
        assertEquals("WAHLBERG",actor.get().getLastName());
    }

    @Test
    public void testFindActorByFirstNameAndLastNameFail(){
        Optional<Actor> actor = actorRepo.findActorByFirstNameAndLastName("DOESNT","EXIST");
        assertTrue(actor.isEmpty());
    }

    @Test
    public void testCreateActor(){
        Actor actor = new Actor();
        actor.setFirstName("Harry");
        actor.setLastName("Phillips");
        Actor savedActor = actorRepo.save(actor);
        assertNotNull(actorRepo.findById(savedActor.getId()));
    }

    //will test the auto generated lastupdated variable
    @Test
    public void testUpdateActor(){
        Actor actor = actorRepo.findById(1L).get();
        LocalDateTime ldtNow = LocalDateTime.now();
        ZonedDateTime zdt = ldtNow.atZone(ZoneId.from(ZoneOffset.UTC));
        Date testDatenow = Date.from(zdt.toInstant());
        actor.setFirstName("TEST");
        actorRepo.save(actor);
        actor = actorRepo.findById(1L).get();
        assertEquals(testDatenow.compareTo(actor.getLastUpdate()),1);
    }



}
