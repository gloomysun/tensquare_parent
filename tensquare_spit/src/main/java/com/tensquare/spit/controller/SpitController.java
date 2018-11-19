package com.tensquare.spit.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        return new Result(StatusCode.OK, true, "查询成功", spitService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Spit> findById(@PathVariable Long id) {
        return new Result<>(StatusCode.OK, true, "查询成功", spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(StatusCode.OK, true, "添加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable Long id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(StatusCode.OK, true, "修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        spitService.deleteById(id);
        return new Result(StatusCode.OK, true, "删除成功");
    }

    /**
     * 根据parentid查询所有吐槽列表
     *
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result<PageResult> findByParentId(@PathVariable Long parentid, @PathVariable Integer page, @PathVariable Integer size) {
        Page<Spit> spits = spitService.findByParentid(parentid, page, size);
        return new Result<>(StatusCode.OK, true, "查询成功", new PageResult(spits.getTotalPages(), spits.getContent()));
    }

    @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable Long id) {
        Long userid = 100000L;
        if (redisTemplate.opsForValue().get("thumbup:" + userid + "_" + id) != null) {
            return new Result(StatusCode.ERROR, false, "不能重复点赞");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set("thumbup:" + userid + "_" + id, "1");
        return new Result(StatusCode.OK, true, "点赞成功");
    }

}
