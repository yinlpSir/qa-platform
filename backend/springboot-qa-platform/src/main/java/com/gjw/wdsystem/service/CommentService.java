package com.gjw.wdsystem.service;

import com.gjw.wdsystem.dto.comment.AddCommentDTO;
import com.gjw.wdsystem.dto.comment.PagingCommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<Object> getCommentsCount(String problemId);
    ResponseEntity<Object> getLatelyCommentCount(String problemId);
    ResponseEntity<Object> getCommentsCountByUserId(String userId);
    ResponseEntity<Object> addComment(AddCommentDTO addCommentDTO);
    ResponseEntity<Object> getCommentsByProblemId(String problemId);
    ResponseEntity<Object> getCommentsByUserId(String userId, PagingCommentDTO pagingCommentDTO);
    ResponseEntity<Object> delComment(String commentId);
}
