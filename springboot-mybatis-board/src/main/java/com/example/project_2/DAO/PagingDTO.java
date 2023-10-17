package com.example.project_2.DAO;

import lombok.Data;

@Data
public class PagingDTO {

    private int pageStart;
    private int pageEnd;
    private boolean next, prev;
    private int total;
    private PagingDAO page;
    public PagingDTO(PagingDAO page, int total) {
        this.page = page;
        this.total = total;

        this.pageEnd = (int) (Math.ceil(page.getPageNum() / 10.0 )) * 10;
        this.pageStart = this.pageEnd - 9;
        int realEnd = (int) (Math.ceil(total * 1.0 / page.getAmount()));
        if (realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }

        this.prev = this.pageStart > 1;
        this.next = this.pageEnd < realEnd;
    }


}
