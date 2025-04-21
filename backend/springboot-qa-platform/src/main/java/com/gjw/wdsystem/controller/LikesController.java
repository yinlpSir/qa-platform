package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @GetMapping("/click")
    public ResponseEntity<Object> clickLike(String userId,String problemId){
        return likesService.clickLike(userId, problemId);
    }

    @GetMapping("/check")
    public ResponseEntity<Object> checkUserClickLiked(String userId,String problemId){
        return likesService.checkUserClickLiked(userId,problemId);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getLikeCountByProblemId(String problemId){
        return likesService.getLikeCountByProblemId(problemId);
    }

    @DeleteMapping
    public ResponseEntity<Object> cancelLike(String userId,String problemId){
        return likesService.cancelLike(userId,problemId);
    }
}
