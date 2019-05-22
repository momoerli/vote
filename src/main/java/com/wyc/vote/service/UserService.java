package com.wyc.vote.service;

import com.wyc.vote.entity.Person;

public interface UserService {
    Person selectUser(int id);
}
