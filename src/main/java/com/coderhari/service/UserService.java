package com.coderhari.service;

import com.coderhari.entity.User;

public interface UserService {
    public User saveUser(User user);

    public boolean existEmailCheck(String email);

}
