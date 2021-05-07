package com.demo3.demo3.film;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnRatingsConverter implements AttributeConverter<Ratings, String> {
    @Override
    public String convertToDatabaseColumn(Ratings enRatings) {
        return enRatings.getRating();

    }

    @Override
    public Ratings convertToEntityAttribute(final String s) {
        return Ratings.fromString(s);
    }
}
