package com.gjw.wdsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotProblemVO {
    private String problemLink; // 问题链接
    private String cover; // 封面
    private String title; // 标题
    private String content; // 问题内容
    private String heat; // 问题热度
}
