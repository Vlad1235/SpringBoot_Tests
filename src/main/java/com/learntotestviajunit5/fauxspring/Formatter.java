package com.learntotestviajunit5.fauxspring;


import com.learntotestviajunit5.model.PetType;
import java.util.Locale;
import org.springframework.expression.ParseException;

public interface Formatter<T> {

    String print(PetType petType, Locale locale);

    PetType parse(String text, Locale locale) throws ParseException, java.text.ParseException;
}
