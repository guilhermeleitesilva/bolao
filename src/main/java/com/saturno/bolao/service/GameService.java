package com.saturno.bolao.service;

import com.saturno.bolao.entity.Game;
import com.saturno.bolao.repository.GameRepository;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Game not found : " + id));
    }

    @Transactional
    public void delete(Long gameId) {
        Game game = findById(gameId);
        gameRepository.delete(game);
    }

    @Transactional
    public Game update(Game game) {
        Game gameExist = findById(game.getId());
        game.setId(gameExist.getId());
        return gameRepository.save(game);
    }
}
