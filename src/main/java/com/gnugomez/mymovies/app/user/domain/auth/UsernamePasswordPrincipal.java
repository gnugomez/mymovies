package com.gnugomez.mymovies.app.user.domain.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Getter
@ToString
@AllArgsConstructor
public class UsernamePasswordPrincipal implements UserDetails {

    private Long id;

    private final String username;

    private final String password;

    private final String firstName;

    private final String lastName;
    private final String role;

    private final boolean accountEnabled;

    private final boolean accountExpired;

    private final boolean accountLocked;

    private final boolean credentialsExpired;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(role).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.accountEnabled;
    }
}
