package com.jvlcode.example.springboot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jvlcode.example.springboot_demo.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
    Optional<UserEntity> findByUsername(String username);
}
