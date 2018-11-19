package com.tensquare.spit.repository;

import com.tensquare.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitRepository extends MongoRepository<Spit, Long> {

    Page<Spit> findByParentid(Long parentid, Pageable pageable);
}
