package com.eshop.admin.user.services;

import com.eshop.admin.user.repositories.UserRepository;
import com.eshop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

}
