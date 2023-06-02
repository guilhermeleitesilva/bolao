package com.saturno.bolao.dto;

import com.saturno.bolao.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

public record UserRecordDto(@NotBlank(message = "Name is required")
                            @Max(value = 50)
                            String name,
                            @Max(value = 50)
                            @NotBlank(message = "Email is required")
                            @Email(message = "Define an valid email")
                            String email,
                            @Max(value = 20)
                            @NotBlank(message = "Username is required")
                            String username) {
    public User convertToUser(){
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
