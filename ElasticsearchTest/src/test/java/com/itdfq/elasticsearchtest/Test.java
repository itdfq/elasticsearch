package com.itdfq.elasticsearchtest;

import com.alibaba.fastjson.JSON;
import com.itdfq.elasticsearchtest.pojo.Movies;
import com.itdfq.elasticsearchtest.service.ESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author GocChin
 * @Date 2021/7/22 15:27
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description: SpringBoot 集成 Elasticsearch
 */
@SpringBootTest
@Slf4j
class Test {

    @Autowired
    private ESService esService;


    @org.junit.jupiter.api.Test
    public void test1() {
        long count = esService.count();
        log.info("总数量为："+count);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        try {
            esService.insert(new Movies("7","河南加油","ABC","2021"));
            log.info("插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("插入失败，错误：{}", JSON.toJSON(e.getMessage()));
        }
    }

    //查询所有
    @org.junit.jupiter.api.Test
    public void test3(){
        List<Movies> all = esService.findAll();
        log.info("查询出来的数据为：{}",all);
    }

//    自定义查询 按照title进行like查询
    @org.junit.jupiter.api.Test
    public void test4(){
        Movies movies = new Movies("123","Kill","123","1234");
        List<Movies> byTitleLike = esService.findByTitleLike(movies);
        for (Movies movies1 : byTitleLike) {
            log.info("查询出来的数据为：{}",movies1);
        }
        log.info("查询出来的总数据为：{}",byTitleLike);
    }

    //删除
    @org.junit.jupiter.api.Test
    public void test5(){
        try {
            esService.delete("7");
            log.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void test6(){
        Movies movies = new Movies();
    }

}
