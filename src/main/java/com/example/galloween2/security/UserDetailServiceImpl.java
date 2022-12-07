package com.example.galloween2.security;

import com.example.galloween2.entities.User;
import com.example.galloween2.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository
                .findOneByEmail(email)
                .orElseThrow(()->new RuntimeException("El usuario con email " + email + " no existe"));

        return new UserDetailsImpl(user);
    }
}
