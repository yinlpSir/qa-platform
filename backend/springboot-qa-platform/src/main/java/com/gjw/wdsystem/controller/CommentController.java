package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.dto.comment.AddCommentDTO;
import com.gjw.wdsystem.dto.comment.PagingCommentDTO;
import com.gjw.wdsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 回答 controller
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 获取problemId的回答总数
     * @param problemId
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<Object> getCommentsCountByProblemId(String problemId) {
        return commentService.getCommentsCount(problemId);
    }

    @GetMapping("/lately/count")
    public ResponseEntity<Object> getLatelyCommentCount(@RequestParam String problemId) {
        return commentService.getLatelyCommentCount(problemId);
    }

    @GetMapping("/count/u")
    public ResponseEntity<Object> getCommentsCountByUserId(String userId) {
        return commentService.getCommentsCountByUserId(userId);
    }

    @GetMapping
    public ResponseEntity<Object> getCommentsByProblemId(@RequestParam String problemId) {
        return commentService.getCommentsByProblemId(problemId);
    }

    @GetMapping("/getCommentsByUserId/{userId}")
    public ResponseEntity<Object> getCommentsByUserId(@PathVariable String userId, PagingCommentDTO pagingCommentDTO) {
        return commentService.getCommentsByUserId(userId,pagingCommentDTO);
    }

    @PostMapping
    public ResponseEntity<Object> addComment(@RequestBody AddCommentDTO addCommentDTO) {
        return commentService.addComment(addCommentDTO);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> delComment(@PathVariable String commentId) {
        return commentService.delComment(commentId);
    }
}
