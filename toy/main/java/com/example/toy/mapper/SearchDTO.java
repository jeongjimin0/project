package com.example.toy.mapper;

import com.example.toy.model.Criteria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class SearchDTO {

    private String keyword;
    private String searchType;
    private String keyword2;


    public SearchDTO(String keyword, String searchType, String keyword2) {
        this.keyword = keyword;
        this.searchType = searchType;
    }




}
