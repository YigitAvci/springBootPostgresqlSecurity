package com.hunter.springbootpostgresql.dataAccess.abstracts;

import com.hunter.springbootpostgresql.core.auth.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ApplicationUserDataAccessObject extends JpaRepository<ApplicationUser, Integer> {

    ApplicationUser findApplicationUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("delete from ApplicationUser where username=:username")
    void deleteApplicationUserByUsername(String username);


}
