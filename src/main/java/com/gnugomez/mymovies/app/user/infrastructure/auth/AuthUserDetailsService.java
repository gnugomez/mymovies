package com.gnugomez.mymovies.app.user.domain;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(userRepository.findByUsername(username).get());
        return userRepository.findByUsername(username)
                .map(User::toUsernamePasswordPrincipal)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
