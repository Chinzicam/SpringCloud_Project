package cn.itcast.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Order(-1)
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        // 2. 获取authorization参数
        String authorization = params.getFirst("authorization");
        // 3. 校验
        if ("admin".equals(authorization)) {
            // 4. 满足需求则放行
            return chain.filter(exchange);
        }
        // 5. 不满足需求，设置状态码，这里的常量底层就是401，在restFul中401表示未登录
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        // 6. 结束处理
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}