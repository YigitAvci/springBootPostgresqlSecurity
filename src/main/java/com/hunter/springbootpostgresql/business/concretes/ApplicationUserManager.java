package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.ApplicationUserService;
import com.hunter.springbootpostgresql.core.auth.ApplicationUser;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessResult;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessDataResult;
import com.hunter.springbootpostgresql.dataAccess.abstracts.ApplicationUserDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserManager implements ApplicationUserService {

    private final ApplicationUserDataAccessObject applicationUserDataAccessObject;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserManager(ApplicationUserDataAccessObject applicationUserDataAccessObject, BCryptPasswordEncoder passwordEncoder) {
        this.applicationUserDataAccessObject = applicationUserDataAccessObject;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomSuccessDataResult<ApplicationUser> createApplicationUser(ApplicationUser applicationUser) {
        String password = applicationUser.getPassword();
        String encryptPassword = passwordEncoder.encode(password);
        applicationUser.setPassword(encryptPassword);
        this.applicationUserDataAccessObject.save(applicationUser);
        return new CustomSuccessDataResult<ApplicationUser>("the application user has been saved to the database", applicationUser);
    }

    @Override
    public List<ApplicationUser> findAllApplicationUsers() {
        return this.applicationUserDataAccessObject.findAll();
    }

    @Override
    public CustomSuccessResult deleteApplicationUser(ApplicationUser applicationUser) {
        this.applicationUserDataAccessObject.deleteApplicationUserByUsername(applicationUser.getUsername());
        return new CustomSuccessResult(String.format("the application user has been deleted : %s", applicationUser.getUsername()));
    }
}
