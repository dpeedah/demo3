package com.demo3.demo3.film;

import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class FilmRepoTests {
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
    public void testFilmGetByNames(){
        Iterable<Film> films = filmrepo.findFilmsByTitleContains("JER");
        int size = IterableUtil.sizeOf(films);
        assertTrue(size > 0);
    }

    @Test
    public void testFilmByGetByTitleFail(){
        Iterable<Film> films = filmrepo.findFilmsByTitleContains("OMSAHDS");
        int size = IterableUtil.sizeOf(films);
        assertTrue(size == 0);
    }

    @Test
    public void testFilmByGetByTitleTrue(){
        ArrayList<Film> films = (ArrayList<Film>) filmrepo.findAll();
        Film a = films.get(2);
        String name = a.getTitle();

        Film b = filmrepo.findFilmByTitle(name);
        String bName = b.getTitle();
        assertEquals(name,bName);
    }

    // All unedited sakila update timestamps have the same date of 2006-02-15 05:03:42
    @Test
    public void testGetLastUpdate(){
        Optional<Film> film = filmrepo.findById(73L);
        Date filmDate = film.get().getLastUpdate();
        Date testDate = Date.from(LocalDateTime.of(2006,02,15,05,03,42,0).toInstant(ZoneOffset.UTC));
        assertEquals(testDate.compareTo(filmDate),0);
    }

    /*@Test
    public void testCreateTrue(){
        Film film = new Film("TEST TITLE 101","Testing Description", 101L, 200L);
        Film savedFilm = filmrepo.save(film);
        assertNotNull(filmrepo.findById(savedFilm.getId()).get());
    }*/

    @Test
    public void testUpdate(){
        Film film = filmrepo.findById(1L).get();
        LocalDateTime ldtNow = LocalDateTime.now();
        ZonedDateTime zdt = ldtNow.atZone(ZoneId.from(ZoneOffset.UTC));
        Date testDatenow = Date.from(zdt.toInstant());
        film.setDescription("Test description");
        filmrepo.save(film);
        film = filmrepo.findById(1L).get();
        int a = testDatenow.compareTo(film.getLastUpdate());
        assertEquals(testDatenow.compareTo(film.getLastUpdate()),1);

    }


}
