package com.alea.pokemon.model.type;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record PokemonUrl(String url) implements CharSequence {

    public PokemonUrl(@NotNull String url) {
        this.url = Optional.of(url)
                .filter(u -> u.startsWith("https://pokeapi.co/api/v2/"))
                .orElseThrow(() -> new IllegalArgumentException("Invalid URL"));
    }

    @Override
    public int length() {
        return url.length();
    }

    @Override
    public char charAt(int index) {
        return url.charAt(index);
    }

    @NotNull
    @Override
    public CharSequence subSequence(int start, int end) {
        return url.subSequence(start, end);
    }

}
