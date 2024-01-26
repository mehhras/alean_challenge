package com.alea.pokemon.model.pokemon;

import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonId;
import com.alea.pokemon.model.type.WholeNumber;
import lombok.*;

@Data
//@ToString
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pokemon {

    private PokemonId id;

    private String name;
    private NaturalNumber height;
    private WholeNumber weight;
    private NaturalNumber baseExperience;

}
