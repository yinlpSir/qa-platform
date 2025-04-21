DROP DATABASE IF EXISTS wd_system;

CREATE DATABASE wd_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE wd_system;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS problem;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS collection;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS follow;

-- 用户表
CREATE TABLE user (
                      id VARCHAR(64) PRIMARY KEY,
                      head_portrait MEDIUMBLOB NOT NULL COMMENT '头像',
                      username VARCHAR(50) UNIQUE NOT NULL COMMENT 'Username',
                      password VARCHAR(255) NOT NULL COMMENT 'Password',
                      phone_number VARCHAR(255) UNIQUE NOT NULL CHECK ( REGEXP_LIKE(phone_number,'^1[3-9]\\d{9}$') ) COMMENT '手机号',
                      introduce VARCHAR(255) DEFAULT '空空如也~' COMMENT '一句话介绍自己',
                      gender CHAR(1) NOT NULL DEFAULT '男' COMMENT 'Gender',
                      age int DEFAULT 0 COMMENT 'Age',
                      role VARCHAR(8) DEFAULT 'COMMON' COMMENT '角色',
                      create_time DATETIME DEFAULT NOW() COMMENT '用户创建时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

# insert user(id, headPortrait, username, password, phoneNumber,gender,age) value
#     ('sdlfskdf','sdflsjdfl','lisid','123123','18629478196','女',18);

-- 关注表
create table follow(
    id VARCHAR(64) PRIMARY KEY,
    followed_user_id varchar(64) not null COMMENT '被关注的用户ID', # 应该设个followed_user_id和user_id不能存在相同的记录的约束,不然插入了两条相同的记录
    user_id varchar(64) not null COMMENT '关注者'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- 问题表
CREATE TABLE problem (
                          id VARCHAR(64) PRIMARY KEY,
                          cover MEDIUMBLOB COMMENT '封面',
                          title VARCHAR(200) UNIQUE NOT NULL COMMENT '标题',
                          content VARCHAR(255) COMMENT '问题内容',
                          page_view BIGINT DEFAULT 0 COMMENT '浏览量(PV)',
                          user_id varchar(64) not null COMMENT '问题发布者',
                          create_time DATETIME DEFAULT NOW() COMMENT '问题创建时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

alter table problem change title title varchar(200);

-- 评论表
CREATE TABLE comment (
                         id VARCHAR(64) PRIMARY KEY,
                         content TEXT NOT NULL COMMENT '评论内容',
                         problem_id varchar(64) not null,
                         user_id varchar(64) not null,
                         create_time DATETIME DEFAULT NOW() COMMENT '评论时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- 问题收藏表
create table collection(
    id varchar(64) PRIMARY KEY,
    problem_id varchar(64) not null,
    user_id varchar(64) not null,
    create_time DATETIME DEFAULT NOW() COMMENT '收藏时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- 问题点赞表
create table likes(
                           id varchar(64) PRIMARY KEY,
                           problem_id varchar(64) not null,
                           user_id varchar(64) not null,
                           create_time DATETIME DEFAULT NOW() COMMENT '点赞时间'
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;