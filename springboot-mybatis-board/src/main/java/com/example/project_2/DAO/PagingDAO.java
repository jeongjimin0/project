package com.example.project_2.DAO;

import com.example.project_2.Repository.BoardMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class PagingDAO {

    private int pageNum; // 현재 페이지 번호
    private int amount; // 페이지 표시 개수
    private int skip; // 페이지 스킵
    private String type; // 검색 타입
    private String keyword; // 검색 키워드

    public PagingDAO(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.skip = (pageNum - 1) * amount;
    }

    public PagingDAO() {
        this(1, 10);
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.skip = (pageNum - 1) * this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.skip = (this.pageNum - 1) * 10;
    }

    public String makeQueryString(int pageNum) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("pageNum", pageNum)
                .queryParam("amount", amount)
                .build()
                .encode();

        return uriComponents.toUriString();
    }

}
