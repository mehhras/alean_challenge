package com.alea.pokemon.dto.pokemon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true)
@Getter
@RequiredArgsConstructor(staticName = "of")
public class PokemonListPairDto {

        final List<PokemonLightInfo> results;
        final String nextUrl;
        final Boolean hasNext;

}
