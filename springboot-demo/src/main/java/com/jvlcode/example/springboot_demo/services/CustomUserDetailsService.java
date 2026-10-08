package com.jvlcode.example.springboot_demo.services;

import com.jvlcode.example.springboot_demo.entity.UserEntity;
import com.jvlcode.example.springboot_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("User not Found"));
        return new User(user.getUsername(),user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
    }
}
