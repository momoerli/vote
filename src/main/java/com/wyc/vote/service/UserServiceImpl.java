package com.wyc.vote.service;

import com.wyc.vote.entity.Person;
import com.wyc.vote.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public Person selectUser(int id) {
        return userMapper.selectUser(id);
    }
}
