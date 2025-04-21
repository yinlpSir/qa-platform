package com.gjw.wdsystem.service.impl;

import com.gjw.wdsystem.mapper.LikesMapper;
import com.gjw.wdsystem.pojo.Likes;
import com.gjw.wdsystem.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service("likesService")
public class LikesServiceImpl implements LikesService {
    @Autowired
    private LikesMapper likesMapper;

    @Override
    public ResponseEntity<Object> clickLike(String userId, String problemId) {
        Likes likes = new Likes(
                UUID.randomUUID().toString().replace("-",""),
                problemId,
                userId
        );
        int affectedRows = likesMapper.insertLikes(likes);
        return ResponseEntity.ok(this.buildResult("点赞成功",null));
    }

    @Override
    public ResponseEntity<Object> checkUserClickLiked(String userId, String problemId) {
        Likes likes = likesMapper.checkUserLikedProblem(userId, problemId);
        return ResponseEntity.ok(likes == null?false:true);
    }

    @Override
    public ResponseEntity<Object> getLikeCountByProblemId(String problemId) {
        return ResponseEntity.ok(this.buildResult("获取成功",likesMapper.getLikesCountByProblemId(problemId)));
    }

    @Override
    public ResponseEntity<Object> cancelLike(String userId, String problemId) {
        int affectedRows = likesMapper.delLikes(userId, problemId);
        return ResponseEntity.ok(this.buildResult("已取消点赞",null));
    }

    private HashMap<String,Object> buildResult(String message, @Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
