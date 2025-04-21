package com.gjw.wdsystem.controller;

import com.gjw.wdsystem.annotation.CurrentUser;
import com.gjw.wdsystem.dto.user.LoginDTO;
import com.gjw.wdsystem.dto.user.RegisterDTO;
import com.gjw.wdsystem.dto.user.UpdateUserBaseInfoDTO;
import com.gjw.wdsystem.dto.user.UpdateUserDTO;
import com.gjw.wdsystem.pojo.User;
import com.gjw.wdsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getCurrentUser")
    public ResponseEntity<Object> getCurrentUser(@CurrentUser User user) {
        return userService.getCurrentUser(user);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<Object> getUserById(@RequestParam String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/recommended")
    public ResponseEntity<Object> getRecommendedUser(){
        return userService.getRecommendedUser();
    }

    @GetMapping("/getHeadPortrait")
    public ResponseEntity<Object> getHeadPortrait(String userId){
        return userService.getHeadPortrait(userId);
    }

    @PutMapping("/modifyHeadPortrait")
    public ResponseEntity<Object> modifyHeadPortrait(String userId, MultipartFile headPortrait){
        return userService.modifyHeadPortrait(userId, headPortrait);
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO){
        return userService.register(registerDTO);
    }

    @PutMapping
    public ResponseEntity<Object> modifyUserById(UpdateUserDTO updateUserDTO) throws IOException {
        return userService.modifyUserById(updateUserDTO);
    }

    @PutMapping("/modifyBaseInfo")
    public ResponseEntity<Object> modifyBaseInfo(@RequestBody UpdateUserBaseInfoDTO updateUserBaseInfoDTO){
        return userService.modifyBaseInfo(updateUserBaseInfoDTO);
    }
}
