package com.wyc.vote.mapper;

import com.wyc.vote.entity.Person;
import com.wyc.vote.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM Person WHERE id = #{id}")
    Person selectUser(int id);

    @Select("SELECT * FROM vote_user WHERE user_name = #{loginName} and user_password=#{password}")
    User getUserByLoginNameAndPassword(String loginName, String password);


    @Select("SELECT " +
            "id as userId," +
            "user_name as username," +
            "user_password as 'password'," +
            "user_type as userType," +
            "user_statue as userStatue  " +
            "FROM vote_user " +
            "WHERE user_name = #{username}")
    User getUserByUsername(String username);



    @Select("select count(*) count from user_add_vote where user_id=#{id}")
    int searchuser_voteSum(Integer id);
}
