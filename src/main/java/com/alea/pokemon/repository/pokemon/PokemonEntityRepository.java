package com.alea.pokemon.repository.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonEntityRepository extends JpaRepository<PokemonEntity, Integer>, JpaSpecificationExecutor<PokemonEntity> {
}
