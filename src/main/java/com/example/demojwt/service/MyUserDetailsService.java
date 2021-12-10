package com.example.demojwt.service;

import com.example.demojwt.models.UserMongoDB;
import com.example.demojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserMongoDB user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found With email: "+ username));

        return UserDetailsImpl.build(user);
    }
}
