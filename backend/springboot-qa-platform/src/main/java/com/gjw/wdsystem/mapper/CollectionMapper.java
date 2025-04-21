package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Collection;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectionMapper {
    @Select("select * from collection where user_id = #{userId}")
    List<Collection> getCollectedByUserId(String userId);
    @Select("select count(*) from collection where user_id = #{userId}")
    int getCountByUserId(String userId);
    @Select("select count(*) from collection where problem_id = #{problemId}")
    int getCountByProblemId(String problemId);
    @Insert("insert into collection(id,problem_id,user_id) values (#{id},#{pi},#{ui})")
    int insertCollection(String id, @Param("ui") String userId, @Param("pi") String problemId);
    @Delete("delete from collection where user_id = #{userId} and problem_id = #{problemId}")
    int delCollection(String userId,String problemId);
    @Select("select * from collection where user_id = #{userId} and problem_id = #{problemId}")
    Collection checkUserCollectedProblem(String userId,String problemId);
    @Delete("delete from collection where problem_id = #{problemId}")
    int delCollectsByProblemId(String problemId);
}
