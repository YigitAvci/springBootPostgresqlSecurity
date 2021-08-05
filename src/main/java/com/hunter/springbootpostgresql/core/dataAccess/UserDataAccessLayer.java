package com.hunter.springbootpostgresql.core.dataAccess;

import com.hunter.springbootpostgresql.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataAccessLayer extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
}
