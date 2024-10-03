package com.blogrestapi.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private String status;
    private List<T> data;
    private int pageSize;//number of post per page
    private int pageNumber;
    private int totalPage;
    private long totalElement;
    private boolean lastPage;
}
