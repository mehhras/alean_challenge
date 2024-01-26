package com.alea.pokemon.api;

import com.alea.pokemon.dto.pokemon.PokemonInfo;
import com.alea.pokemon.dto.pokemon.PokemonLightInfoList;
import com.alea.pokemon.dto.pokemon.PokemonListPairDto;
import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonId;
import com.alea.pokemon.model.type.PokemonUrl;
import com.alea.pokemon.model.type.WholeNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class PokemonApi {

    private final RestTemplate restTemplate;
    private static final String DEFAULT_URL_FOR_ALL = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=20";

    public Optional<Pokemon> getPokemonByURL(PokemonUrl url) {
        return ofNullable(restTemplate.getForObject(url.url(), PokemonInfo.class))
                .map(pokemonInfo -> Pokemon.builder()
                        .id(new PokemonId(pokemonInfo.id()))
                        .name(pokemonInfo.name())
                        .height(new NaturalNumber(pokemonInfo.height()))
                        .weight(new WholeNumber(pokemonInfo.weight()))
                        .baseExperience(ofNullable(pokemonInfo.baseExperience()).map(NaturalNumber::new).orElse(null))
                        .build()
                );
    }


    public PokemonListPairDto getAllPokemons(){
        return getAllPokemons(null);
    }

    public PokemonListPairDto getAllPokemons(PokemonUrl url) {
        final PokemonUrl validUrl = ofNullable(url).orElse(new PokemonUrl(DEFAULT_URL_FOR_ALL));
        return ofNullable(restTemplate.getForObject(validUrl.url(), PokemonLightInfoList.class))
                .map(resp -> PokemonListPairDto.of(resp.results(), resp.next(), ofNullable(resp.next()).isPresent()))
                .orElseGet(() -> PokemonListPairDto.of(List.of(), null, FALSE));
    }

}
