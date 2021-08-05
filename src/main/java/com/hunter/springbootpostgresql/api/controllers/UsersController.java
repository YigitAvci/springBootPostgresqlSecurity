package com.hunter.springbootpostgresql.api.controllers;

import com.hunter.springbootpostgresql.business.abstracts.UserService;
import com.hunter.springbootpostgresql.core.entities.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.ErrorDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @GetMapping("/findUsers")
    public DataResult<List<User>> findUsers() {
        return this.userService.findUsers();
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
