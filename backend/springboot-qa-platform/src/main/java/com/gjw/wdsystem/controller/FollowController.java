package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.annotation.CurrentUser;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    /**
     * 关注
     * @param userId 关注者的id
     * @param followedId 被关注者的id
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> follow(@RequestParam String userId,@RequestParam String followedId) {
        return followService.follow(userId, followedId);
    }

    /**
     * 取消关注
     * 删除userId关注的followedId这个用户,而不是直接通过followId来实现。
     * @param userId 关注者Id
     * @param followedId 被关注者Id
     * @return
     */
    @DeleteMapping("/{userId}/{followedId}")
    public ResponseEntity<Object> unfollow(@PathVariable String userId,@PathVariable String followedId) {
        return followService.unfollow(userId, followedId);
    }

    /**
     * 获取我关注的所有用户
     * @param userId
     * @return
     */
    @GetMapping("/getFollowedUsers")
    public ResponseEntity<Object> getFollowedById(@RequestParam String userId){
        return followService.getFollowedById(userId);
    }

    /**
     * 获取粉丝
     * @return
     */
    @GetMapping("/getFollowers")
    public ResponseEntity<Object> getFollowers(@RequestParam String userId) {
        return followService.getFollowers(userId);
    }

    /**
     * 获取userId的关注数量
     * @param userId
     * @return
     */
    @GetMapping("/followedCount")
    public ResponseEntity<Object> getFollowedCount(@RequestParam String userId){
        return followService.getFollowedCount(userId);
    }

    /**
     * 获取userId的粉丝数量
     * @param userId
     * @return
     */
    @GetMapping("/followersCount")
    public ResponseEntity<Object> getFollowersCount(@RequestParam String userId){
        return followService.getFollowersCount(userId);
    }

    /**
     * 检查userId是否关注了
     * @param userId
     * @param checkedUserId
     * @return
     */
    @GetMapping("/check")
    public ResponseEntity<Object> isUserFollowed(@RequestParam String userId,@RequestParam String checkedUserId){
        return followService.isUserFollowed(userId,checkedUserId);
    }
}
