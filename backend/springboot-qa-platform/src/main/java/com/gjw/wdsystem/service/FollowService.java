package com.gjw.wdsystem.service;

import org.springframework.http.ResponseEntity;

public interface FollowService {
    ResponseEntity<Object> follow(String userId , String followedId);
    ResponseEntity<Object> unfollow(String userId , String followedId);
    ResponseEntity<Object> getFollowedById(String userId);
    ResponseEntity<Object> getFollowers(String userId);
    ResponseEntity<Object> getFollowedCount(String userId);
    ResponseEntity<Object> getFollowersCount(String userId);
    ResponseEntity<Object> isUserFollowed(String userId1, String userId2);
}
