package com.gjw.wdsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    private String id;
    private String problemId;
    private String userId;
    private LocalDateTime createTime;
}
