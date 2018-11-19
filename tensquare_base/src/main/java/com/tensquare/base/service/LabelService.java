package com.tensquare.base.service;

import com.tensquare.base.dao.LabelRepository;
import com.tensquare.base.entity.Label;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    @Transactional
    public void add(Label label) {
        label.setId(Long.toString(idWorker.nextId()));
        labelRepository.save(label);
    }

    @Transactional
    public void update(Label label) {
        labelRepository.save(label);
    }

    @Transactional
    public void delete(String id) {
        labelRepository.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelRepository.findAll(createSpecification(label));
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        return labelRepository.findAll(createSpecification(label), PageRequest.of(page - 1, size));
    }

    private Specification<Label> createSpecification(Label label) {
        return new Specification<Label>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                    list.add(cb.like(root.get("labelname"), "%" + label.getLabelname() + "%"));
                }
                if (label.getState() != null && "".equals(label.getState())) {
                    list.add(cb.equal(root.get("state"), label.getState()));
                }

                Predicate[] predicates = new Predicate[list.size()];
                return cb.and(list.toArray(predicates));
            }
        };
    }
}
