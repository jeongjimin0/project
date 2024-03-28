package com.example.toy.service;


import com.example.toy.mapper.Mappers;
import com.example.toy.model.Category;
import com.example.toy.model.Information;
import com.example.toy.model.Num;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MapperService {

    private final Mappers mappers;
    private final TestService testService;

    public Information getBusiness(String IMG) {

        Optional<Information> information = this.mappers.findById(IMG);
        return information.get();
    }

    public Information getBusiness_2(String IMG) {

        Optional<Information> business = this.mappers.findById_2(IMG);
        return business.get();
    }

    public Information getBusiness_4(String IMG) {

        Optional<Information> business = this.mappers.findById_4(IMG);
        return business.get();
    }

    public Information getBusiness_5(String IMG) {

        Optional<Information> business = this.mappers.findById_5(IMG);
        return business.get();
    }

    public List<Information> getBusiness_7(Num num) {

        List<Information> business = this.mappers.findById_7(num);
        return mappers.findById_7(num);
    }


    public Category fineView(Num num) {

        Optional<Category> code = this.mappers.findAll10(num);
        return code.get();
    }



//    public Business getBusiness2(String IMG) {
//
//        Business business = this.mappers.deleteById(IMG);
//        return business;
//    }
}
