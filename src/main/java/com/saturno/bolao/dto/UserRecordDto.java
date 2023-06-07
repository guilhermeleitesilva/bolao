package com.saturno.bolao.dto;

import com.saturno.bolao.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

public record UserRecordDto(@NotBlank(message = "Name is required")
                            String name,
                            @NotBlank(message = "Email is required")
                            @Email(message = "Define an valid email")
                            String email,
                            @NotBlank(message = "Username is required")
                            String username) {
    public User convertToUser(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setCreateDate(Instant.now());
        return user;
    }
}
