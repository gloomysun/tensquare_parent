package com.tensquare.gathering.service;

import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.repository.GatheringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GatheringService {
    @Autowired
    private GatheringRepository gatheringRepository;

    @Cacheable(value = "gathering", key = "#id")
    public Gathering findById(Long id) {
        return gatheringRepository.findById(id).get();
    }

    @CacheEvict(value = "gathering", key = "#gathering.id")
    public void update(Gathering gathering) {
        gatheringRepository.save(gathering);
    }
}
