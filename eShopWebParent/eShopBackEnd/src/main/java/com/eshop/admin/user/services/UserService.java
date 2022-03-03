package com.eshop.admin.user.services;

import com.eshop.admin.user.repositories.RoleRepository;
import com.eshop.admin.user.repositories.UserRepository;
import com.eshop.common.entity.Role;
import com.eshop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Bean annotation allow us to autowired and bring passwordEncoder to encode our password.
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public void save(User user) {
        encodedPassword(user);
        userRepository.save(user);
    }

    // Method which allow us to get and set the encryption in our password field in db.
    private void encodedPassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

}
