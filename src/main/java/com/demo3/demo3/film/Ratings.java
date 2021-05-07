package com.demo3.demo3.film;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Ratings {
    @JsonProperty G("G"),
    @JsonProperty PG("PG"),
    @JsonProperty PG13("PG-13"),
    @JsonProperty R("R"),
    @JsonProperty NC17("NC-17");

    private static final Map<String, Ratings> LOOKUP = Arrays.stream(values())
            .collect(Collectors.toMap(Ratings::getRating, Function.identity()));

    private final String rating;

    private Ratings(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    @JsonCreator
    public static Ratings fromString(final String string) {
        // You may want to include handling for the case where the given string
        // doesn't map to anything - implementation is up to you.)
        return Optional
                .ofNullable(LOOKUP.get(string))
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}

