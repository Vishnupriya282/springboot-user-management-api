package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}


//This single line gives you:
//
//save()
//
//findAll()
//
//findById()
//
//delete()
//
//update()