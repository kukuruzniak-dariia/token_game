package com.game.token.repositories;

import com.game.token.entities.Game;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryV2;
import io.katharsis.resource.list.ResourceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameResourceRepository implements ResourceRepositoryV2<Game, Long> {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Class<Game> getResourceClass() {
        return Game.class;
    }

    @Override
    public Game findOne(Long id, QuerySpec querySpec) {
        return gameRepository.findById(id).orElse(null);
    }

    @Override
    public ResourceList<Game> findAll(QuerySpec querySpec) {
        return querySpec.apply(gameRepository.findAll());
    }

    @Override
    public ResourceList<Game> findAll(Iterable<Long> ids, QuerySpec querySpec) {
        return querySpec.apply(gameRepository.findAllById(ids));
    }

    @Override
    public <S extends Game> S save(S entity) {
        return gameRepository.save(entity);
    }

    @Override
    public <S extends Game> S create(S entity) {
        return save(entity);
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
}
