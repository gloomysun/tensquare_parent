package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemRepository;
import com.tensquare.qa.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public Page<Problem> findNewListByLabelId(Long labelid, Integer page, Integer size) {
        return problemRepository.findNewListByLabelId(labelid, PageRequest.of(page - 1, size));
    }


    public Page<Problem> findHotListByLabelId(Long labelid, Integer page, Integer size) {
        return problemRepository.findHotListByLabelId(labelid, PageRequest.of(page - 1, size));
    }

    public Page<Problem> findWaitListByLabelId(Long labelid, Integer page, Integer size) {
        return problemRepository.findWaitListByLabelId(labelid, PageRequest.of(page - 1, size));
    }
}
