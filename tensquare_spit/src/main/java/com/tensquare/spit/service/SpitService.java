package com.tensquare.spit.service;

import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.repository.SpitRepository;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    public List<Spit> findAll() {
        return spitRepository.findAll();
    }

    public Spit findById(Long id) {
        return spitRepository.findById(id).get();
    }

    public void add(Spit spit) {
        spit.set_id(idWorker.nextId());
        spit.setPublishtime(new Date());
        spit.setComment(0); //评论数
        spit.setThumbup(0); //点赞数
        spit.setVisits(0);  //浏览数
        spit.setShare(0);   //分享数
        spit.setState(1);
        spitRepository.save(spit);
    }

    public void update(Spit spit) {
        spitRepository.save(spit);
    }

    public void deleteById(Long id) {
        spitRepository.deleteById(id);
    }

    public Page<Spit> findByParentid(Long parentid, Integer page, Integer size) {
        return spitRepository.findByParentid(parentid, PageRequest.of(page - 1, size));
    }

    public void updateThumbup(Long id) {
        //方法1：
//        Spit spit = spitRepository.findById(id).get();
//        spit.setThumbup(spit.getThumbup() + 1);
//        spitRepository.save(spit);
        //方法2：
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
