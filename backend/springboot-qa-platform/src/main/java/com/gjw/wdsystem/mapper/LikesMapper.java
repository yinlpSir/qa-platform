package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Collection;
import com.gjw.wdsystem.pojo.Likes;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikesMapper {

    /**
     * 获取某问题的 点赞数
     * @param problemId
     * @return
     */
    @Select("select count(*) from likes as l where l.problem_id = #{problemId}")
    long getLikesCountByProblemId(String problemId);

    @Insert("insert into likes(id,problem_id,user_id) values(#{l.id}, #{l.problemId},#{l.userId})")
    int insertLikes(@Param("l") Likes likes);

    @Select("select * from likes where user_id = #{userId} and problem_id = #{problemId}")
    Likes checkUserLikedProblem(String userId, String problemId);

    @Delete("delete from likes where user_id = #{userId} and problem_id = #{problemId}")
    int delLikes(String userId,String problemId);

    @Delete("delete from likes where problem_id = #{problemId}")
    int delLikesByProblemId(String problemId);
}
