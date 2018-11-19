package com.tensquare.qa.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping("/newlist/{labelid}/{page}/{size}")
    public Result<List> newlist(@PathVariable Long labelid, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Problem> problems = problemService.findNewListByLabelId(labelid, page, size);
        return new Result(StatusCode.OK, true, "", new PageResult<>(problems.getTotalPages(), problems.getContent()));
    }
    @RequestMapping("/hotlist/{labelid}/{page}/{size}")
    public Result<List> hotlist(@PathVariable Long labelid, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Problem> problems = problemService.findHotListByLabelId(labelid, page, size);
        return new Result(StatusCode.OK, true, "", new PageResult<>(problems.getTotalPages(), problems.getContent()));
    }
    @RequestMapping("/waitlist/{labelid}/{page}/{size}")
    public Result<List> waitlist(@PathVariable Long labelid, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Problem> problems = problemService.findWaitListByLabelId(labelid, page, size);
        return new Result(StatusCode.OK, true, "", new PageResult<>(problems.getTotalPages(), problems.getContent()));
    }
}
