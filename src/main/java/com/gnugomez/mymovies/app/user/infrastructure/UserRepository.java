package com.gnugomez.mymovies.app.user.infrastructure;

import com.gnugomez.mymovies.app.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);
}
