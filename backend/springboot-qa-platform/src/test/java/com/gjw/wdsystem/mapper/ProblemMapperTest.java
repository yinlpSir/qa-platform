package com.gjw.wdsystem.mapper;

import com.gjw.wdsystem.pojo.Problem;
import com.gjw.wdsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProblemMapperTest {
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LikesMapper likesMapper;

    @Test
    public void test1() {
        int num = problemMapper.modifyPageViewByProblemId("96efcccb49b748a598ed8c9a472fd7b5");
        System.out.println(num);
    }

    @Test
    public void test2() {
        List<Problem> problems = problemMapper.getProblemsByKeyword("的");
        System.out.println(problems);
    }

    @Test
    public void test3() {
        //a0b0a19dd12c4d4fad2b20551891df17
        //c9986a0139a740efb8b7511e9fa791dc
        //7edbfbafb3114ed7877a112430d3a4d9
        //8771084db68f4e99acfcb114fd164825
        List<Problem> problems = problemMapper.getFollowedUserProblems("f0e6304e73cd446db680c3a5bd1168f3");
//        System.out.println(problems);
        problems.forEach(p -> {
            System.out.println(p.getTitle()+" "+p.getCreateTime());
        });
    }

    @Test
    public void test4() {
//        long i=commentMapper.getLatelyCommentCount("76c154f086a140df969e6134cd0ea1f7");
//        System.out.println(i);

        List<Problem> popularProblems = problemMapper.getPopularProblems();
        popularProblems.forEach(p -> {
            long l = likesMapper.getLikesCountByProblemId(p.getId());
            long c = commentMapper.getCommentCountByProblemId(p.getId());
            System.out.println(p.getTitle()+l+c);
        });
        System.out.println(popularProblems.size());
    }
}
