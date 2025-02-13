package com.bookingsystem.concurrentbooking.service;

import com.bookingsystem.concurrentbooking.dto.LoginRequest;
import com.bookingsystem.concurrentbooking.dto.SignUpRequest;
import com.bookingsystem.concurrentbooking.entity.User;
import com.bookingsystem.concurrentbooking.repository.UserRepository;
import com.bookingsystem.concurrentbooking.security.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public String register(SignUpRequest signUpRequest) {

        String name = signUpRequest.getName();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String confirmPassword = signUpRequest.getConfirmPassword();
        String role = signUpRequest.getRole();

//        log.info("Registering new user" );
//        log.info(email);
//        log.info(password);
//        log.info(confirmPassword);
//        log.info(role);

        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        if(!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords do not match");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role == null ? "USER" : role);
        userRepository.save(user);

        return "User created";
    }

    public String login(LoginRequest loginRequest) {

        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + loginRequest.getEmail() + " not found"));


        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("In Valid Credentials");
        }

        return jwtUtil.generateToken(user.getEmail());

    }
}
