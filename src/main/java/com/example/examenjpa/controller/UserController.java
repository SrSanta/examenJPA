package com.example.examenjpa.controller;

import com.example.examenjpa.exception.UserNotFountException;
import com.example.examenjpa.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import com.example.examenjpa.domain.User;
import com.example.examenjpa.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"","/"})
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(this.userService.all());
    }

    @PostMapping({"","/"})
    public User addUser(@RequestBody User user) {
        return this.userService.newUser(user);
    }

    @GetMapping("/{id}")
    public User one(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFountException(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }


}
