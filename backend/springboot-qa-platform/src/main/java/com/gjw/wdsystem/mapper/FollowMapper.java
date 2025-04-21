package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Follow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowMapper {

    @Insert("insert into follow(id,followed_user_id,user_id) values (#{id},#{fi},#{ui})")
    int insertFollow(@Param("id") String id,@Param("ui") String userId,@Param("fi") String followedId);

    @Delete("delete from follow where user_id = #{userId} and followed_user_id = #{followedId}")
    int delFollow(String userId,String followedId);

    /**
     * 获取userId关注的人
     * @param userId
     * @return
     */
    @Select("select * from follow where user_id = #{userId} ;")
    List<Follow> getFollowedByUserId(String userId);

    /**
     * 获取userId的粉丝
     * @param userId
     * @return
     */
    @Select("select * from follow where followed_user_id = #{userId} ;")
    List<Follow> getFollowers(String userId);

    @Select("select count(*) from follow where user_id = #{userId}")
    int getFollowedCount(String userId);

    @Select("select count(*) from follow where followed_user_id = #{userId}")
    int getFansCount(String userId);

    @Select("select * from follow where followed_user_id = #{userId2} and user_id = #{userId1}")
    Follow isUserFollowed(String userId1,String userId2);
}
