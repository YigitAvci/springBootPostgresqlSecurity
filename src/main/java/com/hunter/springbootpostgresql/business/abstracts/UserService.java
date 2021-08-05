package com.hunter.springbootpostgresql.business.abstracts;

import com.hunter.springbootpostgresql.core.entities.User;
import com.hunter.springbootpostgresql.core.utilities.results.DataResult;
import com.hunter.springbootpostgresql.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    Result createUser(User user);
    DataResult<User> findUserByEmail(String email);
    DataResult<List<User>> findUsers();
}
