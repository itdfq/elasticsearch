package com.itdfq.elasticsearchtest.service;

import com.itdfq.elasticsearchtest.dao.ESBookRepository;
import com.itdfq.elasticsearchtest.pojo.Movies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author GocChin
 * @Date 2021/7/22 15:14
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Service
@Slf4j
public class ESService {
    @Autowired
    private ESBookRepository esBookRepository;


    private List<Movies> list = new ArrayList<>();


    /**
     * 插入
     *
     * @param movies
     */
    @Transactional(rollbackFor = Exception.class)
    public void insert(Movies movies) {
        log.info("插入数据：{}", movies);
        esBookRepository.save(movies);
    }


    /**
     * 计算总数量
     *
     * @return
     */
    public long count() {
        log.info("开始查询数据总数量");
        return esBookRepository.count();
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Movies> findAll() {
        Iterable<Movies> all = esBookRepository.findAll();
        for (Movies movies : all) {
            log.info("查询出来的数据为：{}", movies);
            list.add(movies);
        }
        return list;
    }

    /**
     * 条件查询
     * 自定义查询可以通过 SpringData  命名规则进行设置方法
     *
     * @param movies
     * @return
     */
    public List<Movies> findByTitleLike(String title) {
        return esBookRepository.findByTitleLike(title);
    }

    /**
     * 通过id删除数据
     *
     * @param id
     */
    public void delete(String id) {
        esBookRepository.deleteById(id);
    }

    /**
     * 更新数据
     *
     * @param movies
     */
    public void update(Movies movies) {
        esBookRepository.save(movies);
    }


    public List<Movies> findByCityLike(String city){
        return esBookRepository.findByCityLike(city);
    }


}
