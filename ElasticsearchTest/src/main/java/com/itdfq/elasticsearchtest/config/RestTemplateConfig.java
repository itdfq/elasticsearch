package com.itdfq.elasticsearchtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author GocChin
 * @Date 2021/7/22 11:15
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
