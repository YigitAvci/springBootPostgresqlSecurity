package com.hunter.springbootpostgresql.business.abstracts;

import com.hunter.springbootpostgresql.core.auth.ApplicationUser;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.CustomSuccessResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApplicationUserService {

    CustomSuccessDataResult<ApplicationUser> createApplicationUser(ApplicationUser applicationUser);
    List<ApplicationUser> findAllApplicationUsers();
    CustomSuccessResult deleteApplicationUser(ApplicationUser applicationUser);
}
