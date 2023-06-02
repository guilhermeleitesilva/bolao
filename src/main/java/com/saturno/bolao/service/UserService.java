package com.saturno.bolao.service;

import com.saturno.bolao.entity.User;
import com.saturno.bolao.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
