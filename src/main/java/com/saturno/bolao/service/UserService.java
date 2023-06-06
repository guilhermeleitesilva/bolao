package com.saturno.bolao.service;

import com.saturno.bolao.entity.User;
import com.saturno.bolao.service.exceptions.BadRequestException;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import com.saturno.bolao.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {

        if (userRepository.existsByUsername(user.getUsername()))
            throw new BadRequestException("Conflict: Username is already in use!");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new BadRequestException("Conflict: Email is already in use!");

        return userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found : " + id));
    }

    @Transactional
    public void delete(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    @Transactional
    public User update(User user) {
        User userExist = findById(user.getId());
        user.setId(userExist.getId());
        return userRepository.save(user);
    }
}
