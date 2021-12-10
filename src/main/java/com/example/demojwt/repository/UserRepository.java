package com.example.demojwt.repository;

import com.example.demojwt.models.UserMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserMongoDB, String> {
    Optional<UserMongoDB> findUserByEmail(String email);
}
