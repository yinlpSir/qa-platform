package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void login(){
        User user = userMapper.getUserByUsername("zs");
        System.out.println(user.getUsername());
    }
    @Test
    public void test1(){
        List<User> users =  userMapper.getRecommendedUser();
        System.out.println(users);
    }
    @Test
    public void test2(){
        int s = 2%0;
    }
}
