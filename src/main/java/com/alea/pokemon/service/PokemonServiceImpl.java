package com.alea.pokemon.service;

import com.alea.pokemon.api.PokemonApi;
import com.alea.pokemon.dto.pokemon.PokemonLightInfo;
import com.alea.pokemon.model.pokemon.Pokemon;
import com.alea.pokemon.model.type.NaturalNumber;
import com.alea.pokemon.model.type.PokemonUrl;
import com.alea.pokemon.repository.pokemon.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonApi pokemonApi;
    private final PokemonRepository repository;

    @Override
    public void save(Pokemon pokemon) {
        repository.save(pokemon);
    }

    @Override
    public List<Pokemon> findHeaviest(NaturalNumber size) {
        return repository.findHeaviest(size);
    }

    @Override
    public List<Pokemon> findHighest(NaturalNumber size) {
        return repository.findHighest(size);
    }

    @Override
    public List<Pokemon> findMostBaseExperience(NaturalNumber size) {
        return repository.findMostBaseExperience(size);
    }


    @Override
    public void crawl() {
        final List<PokemonUrl> pokemons = crawlPokemonUrls();
        final List<Pokemon> pokemonInfos = crawlPokemonInfo(pokemons);
        pokemonInfos.forEach(this::save);
    }

    @Override
    public List<PokemonUrl> crawlPokemonUrls(){
        var list = pokemonApi.getAllPokemons();
        List<String> pokemonUrls = list.results().stream().map(PokemonLightInfo::url).collect(toCollection(ArrayList::new));
        while (list.hasNext()) {
            list = pokemonApi.getAllPokemons(new PokemonUrl(list.nextUrl()));
            var poks = list.results().stream().map(PokemonLightInfo::url).toList();
            pokemonUrls.addAll(poks);
        }
        return pokemonUrls.stream().map(PokemonUrl::new).toList();
    }

    @Override
    public List<Pokemon> crawlPokemonInfo(List<PokemonUrl> urls){
        return urls.parallelStream()
                .map(pokemonApi::getPokemonByURL)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }


}
