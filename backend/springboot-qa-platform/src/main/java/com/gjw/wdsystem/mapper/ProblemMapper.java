package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Problem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProblemMapper {

    /**
     * 获取推荐的问题
     * @return
     */
    @Select("select * from problem")
    List<Problem> getRecommendProblems();

    @Select("select id,title,page_view,user_id,create_time from problem order by rand() limit 15")
    List<Problem> getRandomProblems();

    /**
     * 这里使用了LEFT JOIN来确保即使某个商品没有点赞或评论，它仍然会出现在结果集中（但点赞数和评论数将为0）。COUNT(l.like_id)和COUNT(r.review_id)分别计算每个商品的点赞数和评论数。然后，根据这些数量计算人气分数，并按人气分数降序排序。
     *
     * 请注意，如果存在重复的点赞或评论（例如，由于用户可以多次点赞或评论同一个商品，尽管这在大多数情况下是不常见的），你可能需要使用DISTINCT关键字来确保计数是准确的。但是，如果数据库设计得当，通常每个点赞和评论都应该有唯一的ID，因此COUNT函数应该能够正确计算数量。
     * @return
     */
    @Select("select p.id,p.title,p.page_view,p.user_id,p.create_time,(count(l.id)*0.5 + count(c.id)*0.5) as popular_score from problem p " +
            "left join likes l on l.problem_id = p.id " +
            "left join comment c on c.problem_id = p.id " +
            "group by p.id,p.title " +
            "order by popular_score desc")
    List<Problem> getPopularProblems();

    @Select("select p.id,p.title,p.page_view,p.user_id,p.create_time from problem p where p.create_time >= NOW() - INTERVAL 1 WEEK order by p.create_time desc")
    List<Problem> getLatestProblems();

    /**
     * 获取userId关注的用户的问题
     *      首先获取我关注的用户id，再使用这些id在problem表中查找。然后再通过问题最新发布的时间进行排序，
     * @param userId
     * @return
     */
    @Select("select * from problem p join user u on p.user_id = u.id where p.user_id in " +
            "(select f.followed_user_id from follow f where f.user_id = #{userId}) " +
            "order by p.create_time DESC")
    List<Problem> getFollowedUserProblems(String userId);

    @Select("select * from problem where id = #{problemId}")
    Problem getProblemById(String problemId);

    @Select("select * from problem where user_id = #{userId}")
    Problem getProblemByUserId(String userId);

    @Select("select * from problem where user_id = #{userId}")
    List<Problem> getMineProblemsByUserId(String userId);

    @Select("select * from problem where title like concat('%',#{keyword},'%')")
    List<Problem> getProblemsByKeyword(String keyword);

    @Insert("insert into problem(id,cover,title,content,user_id) values (#{p.id},#{p.cover},#{p.title},#{p.content},#{p.userId})")
    int insertProblem(@Param("p") Problem problem);

    @Update("update problem set page_view = page_view + 1 where id = #{problemId}")
    int modifyPageViewByProblemId(String problemId);

    @Delete("delete from problem where id = #{problemId}")
    int delProblemByProblemId(String problemId);
}
