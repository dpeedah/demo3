package com.demo3.demo3.film;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FilmControllerTest {

    @Autowired
    FilmController controller;

    @Test
    public void fakeTest() throws Exception{
        Long a  = 1L;
        assertNotNull(a);
    }

    @Test
    public void getFilmsTest() throws Exception{
        ResponseEntity<List<Film>> films = controller.getFilms();
        List a = films.getBody();
        assertNotNull(a);
        int b = a.size();
        assertTrue(b > 0);
    }
}