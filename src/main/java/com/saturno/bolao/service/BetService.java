package com.saturno.bolao.service;

import com.saturno.bolao.entity.Bet;
import com.saturno.bolao.repository.BetRepository;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    private final BetRepository betRepository;

    public BetService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Transactional
    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }

    public Bet findById(Long id) {
        return betRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Bet not found : " + id));
    }

    @Transactional
    public void delete(Long betId) {
        Bet bet = findById(betId);
        betRepository.delete(bet);
    }

    @Transactional
    public Bet update(Bet bet) {
        Bet betExist = findById(bet.getId());
        bet.setId(betExist.getId());
        return betRepository.save(bet);
    }
}