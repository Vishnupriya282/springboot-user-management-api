package com.example.demo.controller;

import com.example.demo.model.AuthUser;
import com.example.demo.repository.AuthUserRepository;
import com.example.demo.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthUserRepository repo;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/register")
	public String register(@RequestBody AuthUser user) {

	    user.setPassword(encoder.encode(user.getPassword()));
	    user.setRole("ROLE_USER");

	    repo.save(user);

	    return "Registered successfully";
	}


	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> req) {

	    AuthUser user = repo.findByUsername(req.get("username"))
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    if (!encoder.matches(req.get("password"), user.getPassword())) {
	        throw new RuntimeException("Invalid password");
	    }

	    String token = JwtUtil.generateToken(user.getUsername());

	    return Map.of("token", token);
	}

}
