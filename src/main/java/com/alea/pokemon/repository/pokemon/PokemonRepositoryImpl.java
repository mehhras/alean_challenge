package com.alea.pokemon.repository.pokemon;

import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.alea.pokemon.repository.pokemon.PokemonEntity.Fields.*;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.domain.Sort.by;

@RequiredArgsConstructor
@Repository
public class PokemonRepositoryImpl implements PokemonRepository{

    private final PokemonEntityRepository repository;

    @Override
    public void save(Pokemon pokemon) {
        repository.save(PokemonEntity.mk(pokemon));
    }

    @Override
    public List<Pokemon> findAll() {
        return repository.findAll()
                .stream()
                .map(PokemonEntity::toModel)
                .toList();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Pokemon> findHeaviest(NaturalNumber size) {
        return repository.findAll(by(new Order(DESC, weight)))
                .subList(0, size.intValue())
                .stream()
                .map(PokemonEntity::toModel)
                .toList();
    }

    @Override
    public List<Pokemon> findHighest(NaturalNumber size) {
        return repository.findAll(by(new Order(DESC, height)))
                .subList(0, size.intValue())
                .stream()
                .map(PokemonEntity::toModel)
                .toList();
    }

    @Override
    public List<Pokemon> findMostBaseExperience(NaturalNumber size) {
        return repository.findAll(by(new Order(DESC, baseExperience)))
                .subList(0, size.intValue())
                .stream()
                .map(PokemonEntity::toModel)
                .toList();
    }
}
