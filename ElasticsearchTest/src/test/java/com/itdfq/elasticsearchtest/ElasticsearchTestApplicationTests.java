package com.itdfq.elasticsearchtest;


import com.alibaba.fastjson.JSON;
import com.itdfq.elasticsearchtest.parm.DataParm;
import com.itdfq.elasticsearchtest.parm.Query;
import com.itdfq.elasticsearchtest.parm.QueryParm;
import com.itdfq.elasticsearchtest.parm.QueryString;
import com.itdfq.elasticsearchtest.parm.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class ElasticsearchTestApplicationTests {

    @Autowired
    private RestTemplate restTemplate;


    //入门测试
    @Test
    void contextLoads() {
        String forObject = restTemplate.getForObject("http://localhost:9200/", String.class);
        System.out.println(forObject);
    }

    /**
     * 添加操作（POST请求）
     * <p>
     * 这其实就往索引为 db 类型为 user 的数据库中插入一条
     * id 为 1 的一条数据，这条数据其实就相当于一个
     * 拥有 username/password/age 三个属性的一个实体，就是 JSON 数据
     * <p>
     * 总结：
     * 1，每次操作version都会加一，和乐观锁差不多
     * 2. 访问的地址/db/user/id    当添加数据的id已经存在的时候，会变成更新操作
     * 3. 字段 是一直添加  相当于一张大表，把所有数据的字段全部存起来了
     */
    @Test
    void add() {
        Student student = new Student("小红", "5342", 14);
        String forObject = restTemplate.postForObject("http://localhost:9200/db/user/3",
                new DataParm(1, "测试2", "杭州余杭", "http://itdfq.com", 13), String.class);
        String[] split = forObject.split(",");
        List<String> collect = Arrays.stream(split).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 删除操作 （DELETE请求）
     */
    @Test
    void delete() {
//        删除id为2的数据
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9200/db/user/2", HttpMethod.DELETE, null, String.class);
        if (exchange.getStatusCodeValue() == 200) {
            log.info("返回的数据：{}",exchange.getBody().toString());
        }
    }


    /**
     * 修改操作（PUT）
     */
    @Test
    void update(){
//        修改id为1的数据
        Student student = new Student("韩信", "5342", 14);
//        第一种方式修改
        //设置header信息
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("token","123456");
        //封装信息
        HttpEntity requestEntity =new HttpEntity(student,requestHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9200/db/user/1", HttpMethod.PUT, requestEntity, String.class);
        log.info("返回的数据为：{}",exchange.getBody());
//        第二种方式修改
//        String forObject = restTemplate.postForObject("http://localhost:9200/db/user/1",student,String.class);
//        log.info("返回的数据：{}",forObject);

    }

    /**
     * 搜索全部
     */
    @Test
    void  selectALL(){
        //查询所有数据
//        String forObject = restTemplate.getForObject("http://localhost:9200/_search", String.class);
//        System.out.println(forObject);
        //查询索引为movies的所有数据
        String forObject1 = restTemplate.getForObject("http://localhost:9200/movies/_search", String.class);
        System.out.println(forObject1);
    }
    /**
     * 根据内容查询（基本自由文本搜索：）
     */
    @Test
    void selectByTJ(){
        QueryString queryString = new QueryString();
        queryString.setQuery("测试");
        Query query = new Query();
        query.setQuery_string(queryString);
        QueryParm queryParm = new QueryParm();
        queryParm.setQuery(query);
        log.info("查询的条件为{}",JSON.toJSON(queryParm));
        /**
         *   {"query":{"query_string":{"query":"测试"}}}
         *
         *   查询所有正文中 包含有测试的 记录
         */
        String s = restTemplate.postForObject("http://localhost:9200/_search", JSON.toJSON(queryParm), String.class);
        log.info("查询结果为：{}",s);
    }

    /**
     * 根据字段查询
     * 查询参数： 添加一个fields数组, 数组里面默认的title是总表的字段名 ，从字段为title的一列中查询和query条件有关的值
     * "query": {
     *     "query_string": {
     *       "query": "1",
     *       "fields": [
     *         "title"
     *       ]
     *     }
     */








}
