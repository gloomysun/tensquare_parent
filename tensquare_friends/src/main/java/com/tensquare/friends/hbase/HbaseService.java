package com.tensquare.friends.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class HbaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbaseService.class);



    @Autowired
    private Configuration configuration;
    private Connection connection = null;
    private Admin admin = null;

    @PostConstruct
    public void init() {
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createTable(String tableName, String... columnFamily){
        TableName name = TableName.valueOf(tableName);
        try {
            if(admin.tableExists(name)){
                LOGGER.info("{}表已经存在",tableName);
            }else {
                HTableDescriptor hTableDescriptor = new HTableDescriptor(name);
                //向表中添加列族
                for(String cf:columnFamily){
                    HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
                    hTableDescriptor.addFamily(hColumnDescriptor);
                }
                admin.createTable(hTableDescriptor);
                if(admin.tableExists(name)){
                    LOGGER.info("{}创建成功",tableName);
                    return true;
                }else {
                    LOGGER.info("{}创建失败",tableName);
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
