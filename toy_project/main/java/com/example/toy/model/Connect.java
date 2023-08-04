package com.example.toy.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Connect {
    private int SEQ;
    private int CNT;
    private String TITLE;

    public Connect(int SEQ, int CNT, String TITLE) {
        this.SEQ = SEQ;
        this.CNT = CNT;
        this.TITLE = TITLE;
    }


}
