package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.annotation.CurrentUser;
import com.gjw.wdsystem.dto.problem.PagingProblemDTO;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("/{problemId}")
    public ResponseEntity<Object> getProblem(@PathVariable String problemId){
        return problemService.getProblem(problemId);
    }

    @GetMapping("/wenda/like")
    public ResponseEntity<Object> getProblemsYouLike(@RequestParam String userId){
        return problemService.getProblemsYouLike(userId);
    }

    /**
     * 人气问答
     * @return
     */
    @GetMapping("/wenda/popular")
    public ResponseEntity<Object> getPopularProblems(){
        return problemService.getPopularProblems();
    }

    /**
     * 最新挑战
     * @return
     */
    @GetMapping("/wenda/latest")
    public ResponseEntity<Object> getLatestProblems(){
        return problemService.getLatestProblems();
    }

    @GetMapping("/followedUserProblems/{userId}")
    public ResponseEntity<Object> getFollowedUserProblems(@PathVariable String userId,PagingProblemDTO pagingProblemDTO){
        return problemService.getFollowedUserProblems(userId,pagingProblemDTO);
    }

    @GetMapping("/u/{userId}")
    public ResponseEntity<Object> getProblemByUserId(@PathVariable String userId){
        return problemService.getProblemByUserId(userId);
    }

    @GetMapping("/hot")
    public ResponseEntity<Object> getHotProblems(){
        return problemService.getHotProblems();
    }

    @GetMapping("/recommend")
    public ResponseEntity<Object> getRecommendProblems(PagingProblemDTO pagingProblemDTO){
        return problemService.getRecommendProblems(pagingProblemDTO);
    }

    @GetMapping("/mine/{userId}")
    public ResponseEntity<Object> getMineProblems(@PathVariable String userId, PagingProblemDTO pagingProblemDTO){
        return problemService.getMineProblems(userId,pagingProblemDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchProblems(PagingProblemDTO pagingProblemDTO){
        return problemService.searchProblems(pagingProblemDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProblem(@RequestParam(name = "title") String title,
                                                @RequestParam(name = "content",required = false) String content,
                                                @RequestParam(name = "cover",required = false) MultipartFile cover, // 单张图片大小不能超过2MB
                                                @CurrentUser User user ) throws IOException {
        return problemService.create(title,content,cover,user);
    }

    @DeleteMapping("/{problemId}")
    public ResponseEntity<Object> delProblem(@PathVariable String problemId){
        return problemService.delProblem(problemId);
    }

}
