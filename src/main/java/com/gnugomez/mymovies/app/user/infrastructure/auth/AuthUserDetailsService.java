package com.gnugomez.mymovies.app.user.infrastructure.auth;

import com.gnugomez.mymovies.app.user.domain.User;
import com.gnugomez.mymovies.app.user.infrastructure.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        System.out.println(username);
        System.out.println(userRepository.findByUsername(username).get());
        return userRepository.findByUsername(username)
                .map(User::toUsernamePasswordPrincipal)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
