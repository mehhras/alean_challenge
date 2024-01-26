package com.alea.pokemon.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokemonInfo(Integer id,
                          String name,
                          Integer height,
                          Integer weight,
                          @JsonProperty("base_experience") Integer baseExperience) { }
