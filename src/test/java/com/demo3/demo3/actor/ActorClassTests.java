package com.demo3.demo3.actor;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ActorClassTests {

//    @Test
//    public void testGetId(){
//        Actor actor = new Actor("first","last");
//        Long test = actor.getId();
//        assertTrue(test.equals(1L));
//    }

//    @Test
//    public void testGetLastUpdate(){
//        Actor actor = new Actor("first","last");
//        Date test = actor.getLastUpdate();
//        assertTrue(test.equals(CURRENTDATEHERE?));
//    }

    //testing branch

    @Test
    public void testGetFirstName(){
        Actor actor = new Actor("first","last");
        String test = actor.getFirstName();
        assertEquals("first",actor.getFirstName());
    }

    @Test
    public void testGetFirstNameNotNull(){
        Actor actor = new Actor("first","last");
        String test = actor.getFirstName();
        assertNotNull(actor.getFirstName());
    }

    @Test
    public void testGetLastName(){
        Actor actor = new Actor("first","last");
        String test = actor.getLastName();
        assertTrue(test.equals("last"));
    }

    @Test
    public void testSetLastName() {
        Actor actor = new Actor();
        actor.setLastName("test");
        String test = actor.getLastName();
        assertTrue(test.equals("test"));
    }

    @Test
    public void testSetFirstName() {
        Actor actor = new Actor();
        actor.setFirstName("test");
        String test = actor.getFirstName();
        assertTrue(test.equals("test"));
    }

}
