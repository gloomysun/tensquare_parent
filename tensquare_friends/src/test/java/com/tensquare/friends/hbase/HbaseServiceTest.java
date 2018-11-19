package com.tensquare.friends.hbase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HbaseServiceTest {

    @Autowired
    private HbaseService hbaseService;
    @Test
    public void test1(){
        hbaseService.createTable("springbootHbase","fans","attends");
    }

}