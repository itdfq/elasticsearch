package com.itdfq.elasticsearchtest.parm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author GocChin
 * @Date 2021/7/22 13:01
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataParm {
    private Integer id;
    private String name;
    private String address;
    private String url;
    private Integer age;
}
