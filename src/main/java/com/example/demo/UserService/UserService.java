package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void save_user(User obj) {

    obj.setPassword(passwordEncoder.encode(obj.getPassword()));
        userRepository.save(obj);


    }


    public List<User> get_all_user() {

        return userRepository.findAll();
    }

    public Optional<User> Get_User(String us){


       return  userRepository.findByName(us) ;

    }
}
