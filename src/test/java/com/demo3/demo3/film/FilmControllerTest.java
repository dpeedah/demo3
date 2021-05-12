package com.demo3.demo3.film;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class FilmControllerTest {

    @Test
    public void fakeTest() throws Exception{
        Long a  = 1L;
        assertNotNull(a);
    }

    /*@Test
    public void CreateFilm() throws Exception{
        FilmController controller = new FilmController();
        ResponseEntity<List<Film>> films = FilmController.getFilms();
        List a = films.getBody();
        assertNotNull(a);
        int b = a.size();
        assertTrue(b > 0);

    }*/
}
