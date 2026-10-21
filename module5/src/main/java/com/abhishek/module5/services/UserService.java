package com.abhishek.module5.services;

import com.abhishek.module5.exceptions.ResourceNotFoundException;
import com.abhishek.module5.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
UserDetailsService is a core interface in Spring Security responsible for
loading user-specific data during the authentication process.
It is primarily used by the DaoAuthenticationProvider to retrieve a username, password,
and other attributes required for verifying user credentials.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email "+ username +" not found"));
    }
}
