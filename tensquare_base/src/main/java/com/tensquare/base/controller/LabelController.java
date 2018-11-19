package com.tensquare.base.controller;

import com.tensquare.base.entity.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
@Api(value = "标签接口")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiImplicitParam
    public Result<List> findAll() {
        return new Result<>(StatusCode.OK, true, "查询成功", labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "labelId", value = "标签id", required = true, dataType = "string")
    public Result<Label> findById(@PathVariable String labelId) {
        return new Result<>(StatusCode.OK, true, "查询成功", labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(StatusCode.OK, true, "添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable String labelid, @RequestBody Label label) {
        label.setId(labelid);
        labelService.update(label);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    @RequestMapping(value = "{labelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId) {
        labelService.delete(labelId);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        return new Result(StatusCode.OK, true, "查询成功", labelService.findSearch(label));
    }

    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation(value = "带分页的条件查询")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> labels = labelService.pageQuery(label, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult<Label>(labels.getTotalPages(), labels.getContent()));
    }
}
