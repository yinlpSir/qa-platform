package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.dto.user.UpdateUserBaseInfoDTO;
import com.gjw.wdsystem.dto.user.UpdateUserDTO;
import com.gjw.wdsystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.io.InputStream;
import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 随机获取三个用户当作推荐关注用户
     * 不能把自己返回去了
     * @return
     */
    @Select("select id,head_portrait,username,introduce,gender from user order by rand() limit 3")
    List<User> getRecommendedUser();

    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    @Select("select head_portrait from user where id = #{userId}")
    InputStream getHeadPortraitByUserId(String userId);

    @Update("update user set head_portrait = #{headPortrait} where id = #{userId}")
    int updateHeadPortraitByUserId(String userId,InputStream headPortrait);

    @Select("select * from user where id = #{userId}")
    User getUserById(String userId);

    @Insert(
            "insert into user(id,head_portrait,username,password,phone_number,gender,age) values (#{user.id},#{user.headPortrait},#{user.username},#{user.password},#{user.phoneNumber},#{user.gender},#{user.age})"
    )
    int registerUser(@Param("user") User user);

    @Update("update user set head_portrait = #{hp},username = #{modifiedUser.username},introduce = #{modifiedUser.introduce},gender = #{modifiedUser.gender},age = #{modifiedUser.age} where id = #{modifiedUser.id}")
    int modifyUserById(@Param("modifiedUser") UpdateUserDTO updateUserDTO,@Param("hp") InputStream headPortrait);

    @Update("update user set username = #{baseInfo.username},phone_number = #{baseInfo.phoneNumber},introduce = #{baseInfo.introduce},gender = #{baseInfo.gender},age = #{baseInfo.age} where id = #{baseInfo.id}")
    int modifyUserBaseInfoById(@Param("baseInfo") UpdateUserBaseInfoDTO updateUserBaseInfoDTO);
}
