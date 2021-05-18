package com.demo3.demo3.actor;

import com.demo3.demo3.Demo3Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Application.class)
 public class ActorClassTest {

    @Test
    public void testGetFirstName(){
        Actor actor = new Actor("first", "last");
        assertEquals(actor.getFirstName(),"first");
    }

    @Test
    public void testGetLastName(){
        Actor actor = new Actor("first", "last");
        assertEquals(actor.getLastName(),"last");
    }

    @Test
    public void testSetFirstNameValid() {
        Actor actor = new Actor("first", "last");
        actor.setFirstName("Changed");
        assertEquals(actor.getFirstName(),"Changed");
    }

    // An invalid case where first name is just an integer or real value
    @Test
    public void testSetFirstNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            Actor actor = new Actor();
            actor.setFirstName("***");
        });
    }

    // An invalid case where first name is empty
    @Test
    public void testSetFirstNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            Actor actor = new Actor();
            actor.setFirstName("");
        });
    }

    @Test
    public void testSetLastNameValid() {
        Actor actor = new Actor("first", "last");
        actor.setLastName("Changed");
        assertEquals(actor.getLastName(),"Changed");
    }

    // An invalid case where last name is just an integer or real value
    @Test
    public void testSetLastNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            Actor actor = new Actor();
            actor.setLastName("***");
        });
    }

    // An invalid case where last name is empty
    @Test
    public void testSetLastNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            Actor actor = new Actor();
            actor.setLastName("");
        });
    }

    @Test
    public void testToString(){
        Actor actor = new Actor("first", "last");
        String expectedValue = "Actor{" +
                "id=" + actor.getId() +
                ", firstName='" + "first" + '\'' +
                ", lastName='" + "last" + '\'' +
                '}';
        assertEquals(expectedValue,actor.toString());
    }
}
