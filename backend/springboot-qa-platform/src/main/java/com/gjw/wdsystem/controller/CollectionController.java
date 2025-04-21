package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.dto.collect.PagingCollectDTO;
import com.gjw.wdsystem.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/getCollectedByUserId/{userId}")
    public ResponseEntity<Object> getCollectedByUserId(@PathVariable String userId, PagingCollectDTO pagingCollectDTO) {
        return collectionService.getCollectedByUserId(userId,pagingCollectDTO);
    }

    @GetMapping
    public ResponseEntity<Object> collectionProblem(String userId,String problemId) {
        return collectionService.collectionProblem(userId, problemId);
    }

    @GetMapping("/count")
    public ResponseEntity<Object> getCountByUserId(String userId) {
        return collectionService.getCountByUserId(userId);
    }

    @GetMapping("/p/count")
    public ResponseEntity<Object> getCountByProblemId(String problemId) {
        return collectionService.getCountByProblemId(problemId);
    }

    @DeleteMapping
    public ResponseEntity<Object> cancelCollection(@RequestParam String userId,@RequestParam String problemId){
        return collectionService.cancelCollection(userId,problemId);
    }

    /**
     * 判断用户是否收藏某问题
     * @param userId
     * @param problemId
     * @return
     */
    @GetMapping("/isUserCollectedProblem")
    public ResponseEntity<Object> isUserCollectedProblem(String userId,String problemId){
        return collectionService.isUserCollectedProblem(userId, problemId);
    }
}
