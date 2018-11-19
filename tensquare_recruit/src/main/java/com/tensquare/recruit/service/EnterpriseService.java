package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseRepository;
import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.entity.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public List<Enterprise> findByIshot(Integer ishot) {
        return enterpriseRepository.findByIshot(ishot);
    }
}
