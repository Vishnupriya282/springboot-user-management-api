

package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public User update(Long id, User user) {
        User existing = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        if (existing == null) return null;

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    public Page<User> getAllUsers(Pageable pageable) {
        return repo.findAll(pageable);
    }
}

