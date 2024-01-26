package com.alea.pokemon.dto.pokemon;


import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonId;
import com.alea.pokemon.model.type.WholeNumber;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PokemonDto {

    PokemonId id;
    String name;
    NaturalNumber height;
    WholeNumber weight;
    NaturalNumber baseExperience;

    public static PokemonDto mk(Pokemon pokemon) {
        return PokemonDto.builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
                .height(pokemon.getHeight())
                .weight(pokemon.getWeight())
                .baseExperience(pokemon.getBaseExperience())
                .build();
    }

}
