package com.alea.pokemon.repository.pokemon;

import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonId;
import com.alea.pokemon.model.type.WholeNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import static java.util.Optional.ofNullable;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldNameConstants
public class PokemonEntity {

    @Id
    private Integer id;

    private String name;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;

    public static PokemonEntity mk(Pokemon pokemon) {
        return PokemonEntity.builder()
                .id(pokemon.getId().intValue())
                .name(pokemon.getName())
                .height(pokemon.getHeight().intValue())
                .weight(pokemon.getWeight().intValue())
                .baseExperience(ofNullable(pokemon.getBaseExperience()).map(NaturalNumber::intValue).orElse(null))
                .build();
    }

    public Pokemon toModel() {
        return Pokemon.builder()
                .id(new PokemonId(id))
                .name(name)
                .height(new NaturalNumber(height))
                .weight(new WholeNumber(weight))
                .baseExperience(ofNullable(baseExperience).map(NaturalNumber::new).orElse(null))
                .build();
    }
}
