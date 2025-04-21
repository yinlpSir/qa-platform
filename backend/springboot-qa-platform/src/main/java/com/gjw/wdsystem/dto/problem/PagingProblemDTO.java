package com.gjw.wdsystem.dto.problem;

import lombok.Data;

@Data
public class PagingProblemDTO {
    private String keyword; // 搜索关键字
    private int pageSize;
    private int currentPage;
}
