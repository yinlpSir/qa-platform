package com.gjw.wdsystem.service;

import com.gjw.wdsystem.dto.problem.PagingProblemDTO;
import com.gjw.wdsystem.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProblemService {
    ResponseEntity<Object> getProblem(String problemId);

    /**
     * 获取你喜欢的问题
     * @param userId
     * @return
     */
    ResponseEntity<Object> getProblemsYouLike(String userId);
    ResponseEntity<Object> getPopularProblems();

    /**
     * 获取最新发布的问题
     * @return
     */
    ResponseEntity<Object> getLatestProblems();
    /**
     * 获取关注的用户的问题
     * @param userId
     * @return
     */
    ResponseEntity<Object> getFollowedUserProblems(String userId,PagingProblemDTO pagingProblemDTO);
    ResponseEntity<Object> getProblemByUserId(String userId);
    ResponseEntity<Object> getHotProblems();
    ResponseEntity<Object> getRecommendProblems(PagingProblemDTO pagingProblemDTO);
    ResponseEntity<Object> getMineProblems(String userId,PagingProblemDTO pagingProblemDTO);
    ResponseEntity<Object> searchProblems(PagingProblemDTO pagingProblemDTO);
    ResponseEntity<Object> create(String title, String content, MultipartFile cover, User user) throws IOException;
    ResponseEntity<Object> delProblem(String problemId);
}
