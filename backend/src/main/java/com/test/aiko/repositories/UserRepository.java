package com.test.aiko.repositories;

import com.test.aiko.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByEmail(String username);

    @Query("SELECT u FROM User u where u.email = :email")
    Optional<User> findUser(@Param("email") String email);
}
