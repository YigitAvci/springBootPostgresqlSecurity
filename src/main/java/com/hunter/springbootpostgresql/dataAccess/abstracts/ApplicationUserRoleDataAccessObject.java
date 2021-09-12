package com.hunter.springbootpostgresql.dataAccess.abstracts;

import com.hunter.springbootpostgresql.core.auth.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRoleDataAccessObject extends JpaRepository<ApplicationRole, Integer> {
}
