package com.tensquare.qa.dao;

import com.tensquare.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplyRepository extends JpaRepository<Reply,Long>,JpaSpecificationExecutor<Reply> {
}
