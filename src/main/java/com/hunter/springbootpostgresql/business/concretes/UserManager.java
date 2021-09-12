package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.UserService;
import com.hunter.springbootpostgresql.dataAccess.abstracts.UserDataAccessObject;
import com.hunter.springbootpostgresql.entities.concretes.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDataAccessObject userDataAccessObject;

    @Autowired
    public UserManager(UserDataAccessObject userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }


    @Override
    public Result createUser(User user) {
        this.userDataAccessObject.save(user);
        return new SuccessResult("the user has been created on database");
    }

    @Override
    public DataResult<User> findUserByEmail(String email) {
        return new SuccessDataResult<User>(this.userDataAccessObject.findUserByEmail(email), "the user has been found");
    }

    @Override
    public DataResult<List<User>> findAllUsers() {
        return new SuccessDataResult<List<User>>(this.userDataAccessObject.findAll(), "Users have been found");
    }

}
