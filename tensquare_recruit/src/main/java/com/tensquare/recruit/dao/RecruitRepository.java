package com.tensquare.recruit.dao;

import com.tensquare.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long>, JpaSpecificationExecutor<Recruit> {
    List<Recruit> findTop6ByStateOrderByCreatetime(Integer state);

    List<Recruit> findTop6ByStateNotOrderByCreatetime(Integer state);
}
