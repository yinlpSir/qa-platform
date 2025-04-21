package com.gjw.wdsystem.service;

import com.gjw.wdsystem.dto.collect.PagingCollectDTO;
import org.springframework.http.ResponseEntity;

public interface CollectionService {
    ResponseEntity<Object> getCollectedByUserId(String userId, PagingCollectDTO pagingCollectDTO);
    ResponseEntity<Object> collectionProblem(String userId, String problemId);
    ResponseEntity<Object> getCountByUserId(String userId);
    ResponseEntity<Object> getCountByProblemId(String problemId);
    ResponseEntity<Object> cancelCollection(String userId,String problemId);
    ResponseEntity<Object> isUserCollectedProblem(String userId, String problemId);
}
