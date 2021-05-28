package com.demo3.demo3.film;

import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film,Long> {
   Iterable <Film> findFilmsByTitleContains(String title);
   Film findFilmByTitle(String title);
   Iterable <Film> findFilmsByReleaseYear(Long year);
   Iterable <Film> findFilmsByRating(Ratings rating);

}
