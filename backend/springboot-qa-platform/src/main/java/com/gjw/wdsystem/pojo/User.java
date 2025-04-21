package com.gjw.wdsystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    @JsonIgnore
    private InputStream headPortrait;
    private String headPortraitToString; // // 将headPortrait转成Base 64编码的字符串返回给前端的字段
    private String username;
    @JsonIgnore
    private String password;
    private String phoneNumber;
    private String introduce;
    private String gender;
    private int age;
    private String role; // COMMON | ADMIN
    private LocalDateTime createTime;
    public User(String id,InputStream headPortrait, String username, String password, String phoneNumber, String introduce,String gender,int age,String role,LocalDateTime createTime){
        this.id = id;
        this.headPortrait = headPortrait;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.introduce = introduce;
        this.gender = gender;
        this.age = age;
        this.role = role;
        this.createTime = createTime;
    }
}

