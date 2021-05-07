package com.demo3.demo3.film;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film,Long> {
   Iterable <Film> findFilmsByTitleContains(String title);
   Optional <Film> findFilmByTitle(String title);
   Iterable <Film> findFilmsByReleaseYear(Long year);
   Iterable <Film> findFilmsByRating(Ratings rating);

}
