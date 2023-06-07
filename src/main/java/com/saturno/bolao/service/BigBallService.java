package com.saturno.bolao.service;

import com.saturno.bolao.entity.BigBall;
import com.saturno.bolao.repository.BigBallRepository;
import com.saturno.bolao.service.exceptions.BadRequestException;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BigBallService {

    private final BigBallRepository bigBallRepository;

    public BigBallService(BigBallRepository bigBallRepository) {
        this.bigBallRepository = bigBallRepository;
    }

    @Transactional
    public BigBall save(BigBall bigBall) {

        if (bigBallRepository.existsByName(bigBall.getName()))
            throw new BadRequestException("Conflict: Username is already in use!");

        return bigBallRepository.save(bigBall);
    }

    public Page<BigBall> findAll(Pageable pageable) {
        return bigBallRepository.findAll(pageable);
    }

    public BigBall findById(Long id) {
        return bigBallRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found : " + id));
    }

    @Transactional
    public void delete(Long bigBallId) {
        BigBall bigBall = findById(bigBallId);
        bigBallRepository.delete(bigBall);
    }

    @Transactional
    public BigBall update(BigBall bigBall) {
        BigBall bigBallExist = findById(bigBall.getId());
        bigBall.setId(bigBallExist.getId());
        return bigBallRepository.save(bigBall);
    }
}
