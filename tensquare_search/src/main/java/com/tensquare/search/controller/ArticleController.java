package com.tensquare.search.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.search.entity.Article;
import com.tensquare.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(StatusCode.OK, true, "添加成功");
    }

    @RequestMapping(value = "/search/{keywords}/{page}/{size}", method = RequestMethod.GET)
    public Result findByKeyword(@PathVariable String keywords, @PathVariable Integer page, @PathVariable Integer size) {
        Page pagelist = articleService.findByKeyword(keywords, page, size);
        return new Result(StatusCode.OK, true, "查询成功", new PageResult(pagelist.getTotalPages(), pagelist.getContent()));
    }
}
