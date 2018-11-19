package com.tensquare.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tb_admin")
public class Admin {
    @Id
    private Long id;
    private String loginname;
    private String password;
    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
