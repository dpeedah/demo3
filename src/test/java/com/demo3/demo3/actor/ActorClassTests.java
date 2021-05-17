package com.demo3.demo3.actor;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActorClassTests {

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
    public void testConstructor(){
        Actor actor = new Actor("Constructor", "Test");
        assertTrue(actor.getFirstName().equals("Constructor")&&actor.getLastName().equals("Test"));
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
    public void testSetFirstNameValid() {
        actor.setFirstName("Changed");
        assertTrue(actor.getFirstName().equals("Changed"));
    }

    // An invalid case where first name is just an integer or real value
    @Test
    public void testSetFirstNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
                actor.setFirstName("***");
        });
    }

    // An invalid case where first name is empty
    @Test
    public void testSetFirstNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            actor.setFirstName("");
        });
    }

    @Test
    public void testSetLastNameValid() {
        actor.setLastName("Changed");
        assertTrue(actor.getLastName().equals("Changed"));
    }

    // An invalid case where last name is just an integer or real value
    @Test
    public void testSetLastNameInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            actor.setLastName("***");
        });
    }

    // An invalid case where last name is empty
    @Test
    public void testSetLastNameEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            actor.setLastName("");
        });
    }

    @Test
    public void testToString(){
        String expectedValue = "Actor{" +
                "id=" + actor.getId() +
                ", firstName='" + "Harry" + '\'' +
                ", lastName='" + "Phillips" + '\'' +
                '}';
        assertEquals(expectedValue,actor.toString());
    }
}
