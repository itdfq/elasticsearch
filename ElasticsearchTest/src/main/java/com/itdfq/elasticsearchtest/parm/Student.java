package com.itdfq.elasticsearchtest.parm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author GocChin
 * @Date 2021/7/22 11:22
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String username;
    private String password;
    private Integer age;
}
