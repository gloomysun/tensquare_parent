package com.tensquare.article.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tb_channel")
public class Channel {

    @Id
    private Long id;
    private String name;
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
