package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 获取某问题的评论数
     * @param problemId
     * @return
     */
    @Select("select count(*) from comment as c where c.problem_id = #{problemId}")
    long getCommentCountByProblemId(String problemId);

    /**
     * 获取某个问题最近的回答数
     * 这里算三天为最近
     * INTERVAL 1 WEEK 一周
     * @param problemId
     * @return
     */
    @Select("select count(*) from comment as c where c.problem_id = #{problemId} and create_time >= NOW() - INTERVAL 1 DAY")
    long getLatelyCommentCount(String problemId);

    @Select("select count(*) from comment as c where c.user_id = #{userId}")
    long getCommentCountByUserId(String userId);

    @Select("select * from comment as c where c.problem_id = #{problemId}")
    List<Comment> getCommentsByProblemId(String problemId);

    @Select("select * from comment as c where c.user_id = #{userId}")
    List<Comment> getCommentsByUserId(String userId);

    @Insert("insert into comment(id,content,problem_id,user_id) values (#{c.id},#{c.content},#{c.problemId},#{c.userId})")
    int insertComment(@Param("c")Comment comment);

    @Delete("delete from comment where id = #{commentId}")
    int delCommentById(String commentId);

    @Delete("delete from comment where problem_id = #{commentId}")
    int delCommentsByProblemId(String problemId);
}
