package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    
//    // READ ALL
//    @GetMapping
//    public List<User> getAll() {
//        return service.getAll();
//    }
//http://localhost:8080/api/users?page=1&size=3
    // READ BY ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping
    public Page<User> getUsers(
            @PageableDefault(size = 5) Pageable pageable) {
        return service.getAllUsers(pageable);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}

