package com.example.toy.model;

import lombok.Data;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {
    /* 현재 페이지 번호 */
    private int pageNum;

    /* 페이지 표시 개수 */
    private int amount;

    /* 페이지 skip */
    private int skip;

    /* 검색 타입 */
    private String searchType;

    /* 검색 키워드 */
    private String keyword;
    private String keyword2;



    public Criteria() {
        this(1, 10);
    }

    /* Criteria 생성자 */
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = pageNum * 10;
        this.skip = (pageNum - 1) * amount + 1;

    }


    /* 검색 타입 데이터 배열 변환 */
    public String[] getTypeArr() {
        return searchType == null ? new String[]{} : searchType.split("");
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.skip = (pageNum - 1) * 10 + 1;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.skip = (this.pageNum - 1) * 10 + 1;
    }

    public String makeQueryString(int pageNum) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("pageNum", pageNum)
                .queryParam("amount", pageNum * 10)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .queryParam("keyword2", keyword2)
                .build()
                .encode();


        return uriComponents.toUriString();


    }


}




