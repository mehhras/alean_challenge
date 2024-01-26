package com.alea.pokemon.configuration;

import com.alea.pokemon.model.type.NaturalNumber;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToNaturalNumberConverter implements Converter<String, NaturalNumber> {

    @Override
    public NaturalNumber convert(@NotNull String source) {
        int id = Integer.parseInt(source);
        return new NaturalNumber(id);
    }


}
