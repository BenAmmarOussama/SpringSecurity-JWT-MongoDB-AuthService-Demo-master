package com.example.demojwt.controller;

import com.example.demojwt.jwt.JwtUtil;
import com.example.demojwt.models.*;
import com.example.demojwt.repository.UserRepository;
import com.example.demojwt.service.MyUserDetailsService;
import com.example.demojwt.service.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserReq user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserMongoDB u = userRepository.save(user.getUserMongoDB());

            String userJson = objectMapper.writeValueAsString(user.getUserSQL(u.getId()));
            Message message = MessageBuilder
                    .withBody(userJson.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();

            this.rabbitTemplate.convertAndSend("spring-quarkus", "add_user", message);
            return ResponseEntity.ok(u);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        //System.out.println("AuthRequest=   " + authRequest);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            // final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            final String jwt = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (Exception e){
            // e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
