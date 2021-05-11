package com.demo3.demo3.film;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class FilmClassTests {
    private FilmRepository filmrepo;
    @Test
    public void testFilmId(){
        Optional<Film> film = filmrepo.findById(1L);
        Film rfilm = film.get();
        Long id = rfilm.getId();
    }

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
