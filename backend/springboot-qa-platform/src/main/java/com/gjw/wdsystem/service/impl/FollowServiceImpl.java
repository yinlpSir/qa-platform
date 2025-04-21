package com.gjw.wdsystem.service.impl;

import com.gjw.wdsystem.exception.WdSystemException;
import com.gjw.wdsystem.mapper.FollowMapper;
import com.gjw.wdsystem.mapper.UserMapper;
import com.gjw.wdsystem.pojo.Follow;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("followService")
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private UserMapper userMapper;
    private static final String BASE64_ENCODE_ERROR_MESSAGE = "用户头像Base64编码异常!";
    private static final String BASE64_PREFIX = "data:image/png;base64,";

    @Override
    public ResponseEntity<Object> follow(String userId, String followedId) {
        int affectedRows = followMapper.insertFollow(UUID.randomUUID().toString().replace("-", ""), userId, followedId);
        if(affectedRows >= 1)
            return ResponseEntity.ok(this.buildResult("关注成功",null));
        throw new WdSystemException("关注失败");
    }

    @Override
    public ResponseEntity<Object> unfollow(String userId , String followedId) {
        int affectedRows = followMapper.delFollow(userId,followedId);
        return ResponseEntity.ok(this.buildResult("已取消",null));
    }

    @Override
    public ResponseEntity<Object> getFollowedById(String userId) {
        List<Follow> followed = followMapper.getFollowedByUserId(userId);
        List<HashMap<String,Object>> result = new ArrayList<>();
        followed.forEach(f->{
            var temp = new HashMap<String,Object>();
            User user = userMapper.getUserById(f.getFollowedUserId());
            try {
                temp.put("headPortrait",BASE64_PREFIX+Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE+e);
            }
            temp.put("id",user.getId());
            temp.put("username",user.getUsername());
            temp.put("gender",user.getGender());
            temp.put("introduce",user.getIntroduce());
            temp.put("followId",f.getId());
            result.add(temp);
        });
        return ResponseEntity.ok(this.buildResult("获取成功",result));
    }

    @Override
    public ResponseEntity<Object> getFollowers(String userId) {
        List<Follow> followers = followMapper.getFollowers(userId);
        List<Map> result = new ArrayList<>();
        followers.forEach(f -> {
            var temp = new HashMap<String,Object>();
            User user = userMapper.getUserById(f.getUserId());
            temp.put("id",user.getId());
            try {
                temp.put("headPortrait",BASE64_PREFIX+ Base64.getEncoder().encodeToString(user.getHeadPortrait().readAllBytes()));
            } catch (IOException e) {
                throw new RuntimeException(BASE64_ENCODE_ERROR_MESSAGE,e);
            }
            temp.put("username",user.getUsername());
            temp.put("gender",user.getGender());
            temp.put("introduce",user.getIntroduce());
            temp.put("followId",f.getId());
            result.add(temp);
        });
        return ResponseEntity.ok(this.buildResult("获取成功",result));
    }

    @Override
    public ResponseEntity<Object> getFollowedCount(String userId) {
        return ResponseEntity.ok(this.buildResult("获取成功",followMapper.getFollowedCount(userId)));
    }

    @Override
    public ResponseEntity<Object> getFollowersCount(String userId) {
        return ResponseEntity.ok(this.buildResult("获取成功",followMapper.getFansCount(userId)));
    }

    @Override
    public ResponseEntity<Object> isUserFollowed(String userId1, String userId2) {
        Follow follow = followMapper.isUserFollowed(userId1, userId2);
        if(Objects.nonNull(follow))
            return ResponseEntity.ok(this.buildResult("检查成功",true)); // true表示关注了
        return ResponseEntity.ok(this.buildResult("检查成功",false));
    }

    private HashMap<String,Object> buildResult(String message,@Nullable Object data){
        var result = new HashMap<String,Object>();
        result.put("message",message);
        result.put("data",data);
        return result;
    }
}
