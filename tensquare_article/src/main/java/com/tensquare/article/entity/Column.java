package com.tensquare.article.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "tb_column")
public class Column {
    @Id
    private Long id;
    private String name;
    private String summary;
    private Long userid;
    private Date createtime;
    private Date checktime;
    private Integer state;
}
