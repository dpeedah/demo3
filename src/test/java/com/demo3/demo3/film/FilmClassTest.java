package com.demo3.demo3.film;

import com.demo3.demo3.Demo3Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Application.class)
 public class FilmClassTest {

    @Test
    public void createValid(){
        Film film = new Film("Hello title","description",2001L,120L);
        assertEquals(null,film.getId());
        assertEquals("Hello title",film.getTitle());
        assertNull(film.getRating());
    }

    @Test
    public void createValid2(){
        Film film = new Film("Hello title","description",2001L,120L,120L,Ratings.NC17);
        assertEquals(null,film.getId());
        assertEquals("Hello title",film.getTitle());
        assertEquals(Ratings.NC17,film.getRating());
        assertEquals(null,film.getAllActors());
    }


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
        assertNull(test);
    }

    // An invalid case where the description given is empty, but the current description is valid,
    // an illegal argument exception is returned as you are not replacing with a valid description
    @Test
    public void testDescEmpty(){
        assertThrows(IllegalArgumentException.class,()->{
            Film film = new Film();
            film.setDescription("Valid description!");
            film.setDescription("");
            String test = film.getDescription();
        });
    }

    @Test
    public void testYearValid(){
        Film film = new Film();
        film.setReleaseYear(2005L);
        Long newyear = film.getReleaseYear();
        assertEquals(2005L,newyear);
    }

    @Test
    public void testYearInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            Film film = new Film();
            film.setReleaseYear(45000L);
        });

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
    @Test
    public void testLengthEmpty(){
        Film film = new Film();
        film.setLengthMinutes(77L);
        Long newlength = film.getLengthMinutes();
        assertEquals(77L,newlength);
        assertThrows(IllegalArgumentException.class,()->{
            film.setLengthMinutes(-2L);
        });
        newlength = film.getLengthMinutes();
        assertEquals(77L,newlength);

    }

    //Invalid length of a single movie (No movie is 10 Hours long).
    // Setting a length when no length is currently present returns an exception
    @Test
    public void testLengthInvalid(){
        assertThrows(IllegalArgumentException.class,()->{
            Film film = new Film();
            film.setLengthMinutes(12000L);
        });
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
