package com.demo3.demo3.actor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActorClassTests {
    @Test
    public void testSetLastName() {
        Actor actor = new Actor();
        actor.setLastName("test");
        assertTrue(actor.getLastName() == "test");
    }

    @Test
    public void testSetFirstName() {
        Actor actor = new Actor();
        actor.setFirstName("test");
        assertTrue(actor.getFirstName() == "test");
    }

}
