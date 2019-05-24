package com.wyc.vote.service;

import com.wyc.vote.entity.Person;
import com.wyc.vote.entity.User;

public interface UserService {

    /**
     * get user by id.
     * @param id
     * @return
     */
    Person selectUser(int id);


    /**
     * get user by username.
     * @param username
     * @return
     */
    User getUserByUsername(String username);

}
