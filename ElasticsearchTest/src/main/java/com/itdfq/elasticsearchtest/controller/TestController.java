package com.itdfq.elasticsearchtest.controller;

import com.alibaba.fastjson.JSON;
import com.itdfq.elasticsearchtest.config.RestTemplateConfig;
import com.itdfq.elasticsearchtest.pojo.Movies;
import com.itdfq.elasticsearchtest.service.ESService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author GocChin
 * @Date 2021/7/22 11:15
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@RestController
@Slf4j
public class TestController  {
    @Autowired
    private RestTemplateConfig restTemplate;

    @Autowired
    private ESService esService;

    @GetMapping("/test")
    public String test4(){
        Movies movies = new Movies("123","Kill","123","1234");
        log.info("查询的id是：{}",movies.getId());
        List<Movies> byTitleLike = null;
        try {
            byTitleLike = esService.findByTitleLike(movies);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("报错:{}", JSON.toJSON(e.getMessage()));
        }
        for (Movies movies1 : byTitleLike) {
            log.info("查询出来的数据为：{}",movies1);
        }
        log.info("查询出来的总数据为：{}",byTitleLike);
        return byTitleLike.toString();
    }


}
