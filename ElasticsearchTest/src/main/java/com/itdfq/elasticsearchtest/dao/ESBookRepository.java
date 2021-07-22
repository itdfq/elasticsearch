package com.itdfq.elasticsearchtest.dao;

import com.itdfq.elasticsearchtest.pojo.Movies;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @Author GocChin
 * @Date 2021/7/22 15:12
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description: 继承这个接口，实现CRUD
 */

public interface ESBookRepository extends ElasticsearchRepository<Movies, String> {

    List<Movies> findByTitleLike(String title);
}
