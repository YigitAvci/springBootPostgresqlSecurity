package com.hunter.springbootpostgresql.core.auth;

import com.hunter.springbootpostgresql.business.abstracts.ApplicationUserService;
import com.hunter.springbootpostgresql.dataAccess.abstracts.ApplicationUserDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private ApplicationUserDataAccessObject applicationUserDataAccessObject;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserDataAccessObject.findApplicationUserByUsername(username);
        ApplicationUserDetails applicationUserDetails;
        if(applicationUser != null) {
            applicationUserDetails = new ApplicationUserDetails();
            applicationUserDetails.setApplicationUser(applicationUser);
        }else {
            throw new UsernameNotFoundException(String.format("the user has not been found : %s", username));
        }
        return applicationUserDetails;
    }
}
