package com.tensquare.gathering.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.service.GatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gathering")
public class GatheringController {
    @Autowired
    private GatheringService gatheringService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Gathering> findById(@PathVariable Long id) {
        return new Result(StatusCode.OK, true, "查询成功", gatheringService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result<Gathering> update(@PathVariable Long id, @RequestBody Gathering gathering) {
        gathering.setId(id);
        return new Result(StatusCode.OK, true, "查询成功", gatheringService.findById(id));
    }
}
