package com.demo3.demo3.film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmClassTests {

    @Autowired
    private FilmRepository filmrepo;

    @Test
    public void testFilmId(){
        Optional<Film> film = filmrepo.findById(1L);
        Film rfilm = film.get();
        Long id = rfilm.getId();
        assertEquals(1L,id);
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
