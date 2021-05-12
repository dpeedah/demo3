package com.demo3.demo3.actor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActorControllerTests {

    @Autowired
    private ActorController actorController;

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
    public void testGetActors(){
        Iterable<Actor> actors = actorController.getActors();
        assertNotNull(actors);
    }

    @Test
    public void testFindActorByIdValid(){
        Optional<Actor> actor = actorController.findActorById(1L);
        assertFalse(actor.isEmpty());
    }

    @Test
    public void testFindActorByIdInvalid(){
        Optional<Actor> actor = actorController.findActorById(101010101L);
        assertTrue(actor.isEmpty());
    }

//    @Test
//    public void testDeleteActor(){
//        actorController.deleteActor(209L);
//        Optional<Actor> actor = actorController.findActorById(209L);
//        assertTrue(actor.isEmpty());
//    }

}
