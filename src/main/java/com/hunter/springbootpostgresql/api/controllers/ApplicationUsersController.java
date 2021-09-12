package com.hunter.springbootpostgresql.api.controllers;

import com.hunter.springbootpostgresql.business.abstracts.ApplicationUserService;
import com.hunter.springbootpostgresql.business.concretes.ApplicationUserManager;
import com.hunter.springbootpostgresql.core.auth.ApplicationUser;
import com.hunter.springbootpostgresql.core.utilities.results.*;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applicationUsers")
public class ApplicationUsersController {

    private ApplicationUserManager applicationUserManager;

    @Autowired
    public ApplicationUsersController(ApplicationUserManager applicationUserManager) {
        this.applicationUserManager = applicationUserManager;
    }

    @PostMapping("/createApplicationUser")
    public CustomDataResult createApplicationUser(@Valid @RequestBody ApplicationUser applicationUser) {
        return this.applicationUserManager.createApplicationUser(applicationUser);
    }

    @DeleteMapping("/deleteApplicationUser")
    public CustomResult deleteApplicationUser(@RequestBody ApplicationUser applicationUser) {
        return this.applicationUserManager.deleteApplicationUser(applicationUser);
    }

    @GetMapping("/findAllApplicationUsers")
    public List<ApplicationUser> findAllApplicationUsers() {
        return this.applicationUserManager.findAllApplicationUsers();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ErrorDataResult<>(validationErrors, "validation errors");
    }
}
