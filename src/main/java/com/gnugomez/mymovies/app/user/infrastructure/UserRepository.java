package com.gnugomez.mymovies.app.user.infrastructure;

import com.gnugomez.mymovies.app.user.domain.User;
import com.gnugomez.mymovies.app.user.domain.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Primary
public interface JpaUserRepository extends JpaRepository<User, String>, UserRepository {

    Optional<User> findByUsername(String username);
}
