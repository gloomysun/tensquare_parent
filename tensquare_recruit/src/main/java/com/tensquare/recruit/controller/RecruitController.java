package com.tensquare.recruit.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.recruit.dao.RecruitRepository;
import com.tensquare.recruit.entity.Recruit;
import com.tensquare.recruit.service.RecruitService;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;


    @RequestMapping(value = "/search/recommend", method = RequestMethod.GET)
    public Result recomment() {
        return new Result(StatusCode.OK, true, "", recruitService.findTop6ByStateOrderByCreatetime());
    }
    @RequestMapping(value = "/search/newlist", method = RequestMethod.GET)
    public Result newlist() {
        return new Result(StatusCode.OK, true, "", recruitService.findTop6ByStateNotOrderByCreatetime());
    }
}
