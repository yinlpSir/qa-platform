package com.gjw.wdsystem.dto.comment;

import lombok.Data;

@Data
public class AddCommentDTO {
    private String content;
    private String problemId;
    private String userId;
}
