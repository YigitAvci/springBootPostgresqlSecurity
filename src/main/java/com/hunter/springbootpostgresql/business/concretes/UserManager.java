package com.hunter.springbootpostgresql.business.concretes;

import com.hunter.springbootpostgresql.business.abstracts.UserService;
import com.hunter.springbootpostgresql.core.dataAccess.UserDataAccessLayer;
import com.hunter.springbootpostgresql.core.entities.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessDataResult;
import com.hunter.springbootpostgresql.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDataAccessLayer userDataAccessLayer;

    @Autowired
    public UserManager(UserDataAccessLayer userDataAccessLayer) {
        this.userDataAccessLayer = userDataAccessLayer;
    }

    @Override
    public Result createUser(User user) {
        this.userDataAccessLayer.save(user);
        return new SuccessResult("the user has been created on database");
    }

    @Override
    public DataResult<User> findUserByEmail(String email) {
        return new SuccessDataResult<User>(this.userDataAccessLayer.findUserByEmail(email), "the user has been found");
    }

    @Override
    public DataResult<List<User>> findUsers() {
        return new SuccessDataResult<List<User>>(this.userDataAccessLayer.findAll(), "Users have been found");
    }
}
