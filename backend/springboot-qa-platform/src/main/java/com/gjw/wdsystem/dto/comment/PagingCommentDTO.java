package com.gjw.wdsystem.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingCommentDTO {
    private int pageSize;
    private int currentPage;
}
