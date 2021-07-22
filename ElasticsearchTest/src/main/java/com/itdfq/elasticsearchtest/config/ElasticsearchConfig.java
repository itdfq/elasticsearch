package com.itdfq.elasticsearchtest.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @Author GocChin
 * @Date 2021/7/22 15:06
 * @Blog: itdfq.com
 * @QQ: 909256107
 * @Description:
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;

    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;

    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
