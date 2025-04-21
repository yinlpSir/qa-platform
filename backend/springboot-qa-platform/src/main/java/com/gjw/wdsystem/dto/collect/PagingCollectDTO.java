package com.gjw.wdsystem.dto.collect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingCollectDTO {
    private int pageSize;
    private int currentPage;
}
