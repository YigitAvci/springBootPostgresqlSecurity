package com.hunter.springbootpostgresql.dataAccess.abstracts;

import com.hunter.springbootpostgresql.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataAccessObject extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
