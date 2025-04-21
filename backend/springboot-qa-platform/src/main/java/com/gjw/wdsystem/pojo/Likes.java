package com.gjw.wdsystem.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    private String id;
    private String problemId;
    private String userId;
    private LocalDateTime createTime;
    public Likes(String id, String problemId, String userId){
        this.id = id;
        this.problemId = problemId;
        this.userId = userId;
    }
}
