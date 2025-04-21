package com.gjw.wdsystem.service;

import org.springframework.http.ResponseEntity;

public interface LikesService {
    ResponseEntity<Object> clickLike(String userId,String problemId);
    ResponseEntity<Object> checkUserClickLiked(String userId,String problemId);
    ResponseEntity<Object> getLikeCountByProblemId(String problemId);
    ResponseEntity<Object> cancelLike(String userId,String problemId);
}
