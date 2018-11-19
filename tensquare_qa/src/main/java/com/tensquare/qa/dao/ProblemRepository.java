package com.tensquare.qa.dao;

import com.tensquare.qa.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProblemRepository extends JpaRepository<Problem, Long>, JpaSpecificationExecutor<Problem> {

    @Query(value = "SELECT * FROM tb_problem p INNER JOIN tb_pl pl ON p.id=pl.problemid WHERE pl.labelid=? ORDER BY createtime desc", nativeQuery = true)
    Page<Problem> findNewListByLabelId(Long labelId, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem p INNER JOIN tb_pl pl ON p.id=pl.problemid WHERE  pl.labelid=? ORDER BY reply desc", nativeQuery = true)
    Page<Problem> findHotListByLabelId(Long labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem p INNER JOIN tb_pl pl ON p.id=pl.problemid WHERE  pl.labelid=? AND p.reply=0 ORDER BY createtime desc", nativeQuery = true)
    Page<Problem> findWaitListByLabelId(Long labelid, Pageable pageable);
}
