package com.demo3.demo3.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmClassTests {


    @Test
    public void testTitle(){
        Film film = new Film();
        film.setTitle("first");
        String test = film.getTitle();
        assertNotNull(test);
        assertEquals("first",test);
    }

    @Test
    public void testDesc(){
        Film film = new Film();
        film.setDescription("Description");
        String test = film.getDescription();
        assertNotNull(test);
        assertEquals("Description",test);
    }

    // An invalid case where the description is just an integer or real value
    @Test
    public void testDescInvalid() throws Exception{
        Film film = new Film();
        film.setDescription("5555");
        String test = film.getDescription();
        assertEquals(null,test);
    }

    // An invalid case where the description given is empty, but the current description is valid,
    // an illegal argument exception is returned as you are not replacing with a valid description
    @Test(expected = IllegalArgumentException.class)
    public void testDescEmpty(){
        Film film = new Film();
        film.setDescription("Valid description!");
        film.setDescription("");
        String test = film.getDescription();
    }

    @Test
    public void testYearValid(){
        Film film = new Film();
        film.setReleaseYear(2005L);
        Long newyear = film.getReleaseYear();
        assertEquals(2005L,newyear);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testYearInvalid(){
        Film film = new Film();
        film.setReleaseYear(45000L);
    }

    //valid test utilising a valid length for a movie
    @Test
    public void testLengthValid(){
        Film film = new Film();
        film.setLengthMinutes(77L);
        Long newlength = film.getLengthMinutes();
        assertEquals(77L,newlength);
    }

    // after setting a valid length, an invalid string given afterwards should result in the same
    // length being present, and being replaced, should not change current length
    @Test(expected = IllegalArgumentException.class)
    public void testLengthEmpty(){
        Film film = new Film();
        film.setLengthMinutes(77L);
        Long newlength = film.getLengthMinutes();
        assertEquals(77L,newlength);
        film.setLengthMinutes(-2L);
        newlength = film.getLengthMinutes();
        assertEquals(77L,newlength);

    }

    //Invalid length of a single movie (No movie is 10 Hours long).
    // Setting a length when no length is currently present returns an exception
    @Test(expected = IllegalArgumentException.class)
    public void testLengthInvalid(){
        Film film = new Film();
        film.setLengthMinutes(600L);
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
