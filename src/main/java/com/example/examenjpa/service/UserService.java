package com.example.examenjpa.service;

import com.example.examenjpa.domain.User;
import com.example.examenjpa.exception.UserNotFountException;
import com.example.examenjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> all(){return this.userRepository.findAll();}

    public User newUser(User user) {
        return this.userRepository.save(user);
    }

    public User one(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFountException(id));
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
//    public User replace(Long id, User user) {
//        return this.userRepository.findById(id).map( u -> (id.equals(user.getId())  ?
//                this.userRepository.save(user) : null)).orElseThrow(() -> new UserNotFountException(id));
//    }
}
