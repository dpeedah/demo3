package com.demo3.demo3.film;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
public class FilmClassTests {

    @Test
    public void testGetTitle(){
        Film film = new Film();
        film.setTitle("first");
        String test = film.getTitle();
        assertNotNull(test);
        assertEquals("first",test);
    }

    @Test
    public void testGetDesc(){
        Film film = new Film();
        film.setDescription("Description");
        String test = film.getDescription();
        assertNotNull(test);
        assertEquals("Description",test);
    }

    @Test
    public void testRatingConversion(){
        Film film = new Film();
        film.setRating(Ratings.NC17);
        Ratings test = film.getRating();
        String ratingstr = test.getRating();
        assertEquals(Ratings.NC17,test);
        assertEquals("NC-17",ratingstr);
    }


}
