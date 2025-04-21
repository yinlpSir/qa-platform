package com.gjw.wdsystem.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private String id;
    @JsonIgnore // json序列化时忽略这个InputStream类型的cover。因为InputStream如果要序列化应该要指定的序列化器吧。
    private InputStream cover;
    private String coverToString; // 将cover转成Base 64编码的字符串返回给前端的字段
    private String title;
    private String content;
    private int pageView;
    private String userId;
    private LocalDateTime createTime;
}
