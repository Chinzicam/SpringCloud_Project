package cn.itcast.order.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EurekaConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    /**
//     * ribbon负载均衡规则 代码或者配置文件
//     * @return
//     */
//    @Bean
//    public IRule nacosRule() {
//        return new NacosRule();
//    }
}
