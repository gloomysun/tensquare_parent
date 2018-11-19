package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitRepository;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecruitService {

    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private IdWorker idWorker;

    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    public Recruit findById(Long id) {
        return recruitRepository.findById(id).get();
    }

    @Transactional
    public void add(Recruit recruit) {
        recruit.setId(idWorker.nextId());
        recruitRepository.save(recruit);
    }

    @Transactional
    public void update(Recruit recruit) {
        recruitRepository.save(recruit);
    }

    @Transactional
    public void delete(Long id) {
        recruitRepository.deleteById(id);
    }


    public List<Recruit> findTop6ByStateOrderByCreatetime() {
        return recruitRepository.findTop6ByStateOrderByCreatetime(2);
    }

    public List<Recruit> findTop6ByStateNotOrderByCreatetime(){
        return recruitRepository.findTop6ByStateNotOrderByCreatetime(0);
    }
}
