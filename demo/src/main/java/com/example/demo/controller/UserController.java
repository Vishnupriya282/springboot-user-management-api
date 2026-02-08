//
//package com.example.demo.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//
//@RestController
//@RequestMapping("/api")
//public class HelloController {
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello Vishnupriya";
//    }
//
//    @GetMapping("/bye")
//    public String bye() {
//        return "Bye Vishnupriya";
//    }
//    @GetMapping("/user")
//    public Map<String, String> getUser() {
//        return Map.of(
//                "name", "Vishnupriya",
//                "role", "Backend Dev"
//        );
//    }
//    @GetMapping("/user-object")
//    public User getUserObject() {
//        return new User(1L, "Vishnupriya", "vishnu@gmail.com");
//    }
//    @GetMapping("/add")
//    public int add() {
//        return 5 + 3;
//    }
//    @Autowired
//    private UserRepository repo;
//
//    @PostMapping("/user")
//    public User createUser(@RequestBody User user) {
//        return repo.save(user);
//    }
//
//    @GetMapping("/user")
//    public List<User> getAllUsers() {
//        return repo.findAll();
//    }
//
//    @PostMapping("/user")
//    public User createUser(@RequestBody User user) {
//        return user;
//    }
//
//}

package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    // READ ALL
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
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

