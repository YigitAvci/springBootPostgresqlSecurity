package com.hunter.springbootpostgresql.api.controllers;

import com.hunter.springbootpostgresql.business.concretes.UserManager;
import com.hunter.springbootpostgresql.entities.concretes.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.ErrorDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
 public class UsersController {
    private UserManager userManager;

    @Autowired
    public UsersController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(this.userManager.createUser(user));
    }

    @GetMapping("/findAllUsers")
    public DataResult<List<User>> findAllUsers() {
        return this.userManager.findAllUsers();
    }

    @GetMapping("/findUserByEmail")
    public DataResult<User> findUserByEmail(@RequestParam String email) {
        return this.userManager.findUserByEmail(email);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "validation errors");
        return errors;
    }
}
