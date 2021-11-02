package com.itdfq.elasticsearchtest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author GocChin
 * @Date 2021/7/22 15:08
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Data
@Document(indexName = "movies",createIndex = true)
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    //在ES中的唯一ID，此处我们用一个uuid
    @Id
    private String id;
    @Field(index = false , type = FieldType.Text)
    private String title;
    @Field(index = false , type = FieldType.Text)
    private String director;
    @Field(index = false , type = FieldType.Text)
    private String year;
    private String city;
}
