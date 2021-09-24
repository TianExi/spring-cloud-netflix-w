package wangxx.example.gatewayserver.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description：
 * @auther: wangxx
 * @create: 2021/8/27 10:13
 * @version: 1.0
 * @main: wangxx1993ing@163.com
 */

@Component
@Slf4j
public class JnlGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==========开始记录交易流水==========");
        log.info("生成时间戳：" + System.currentTimeMillis());
        log.info("==========完成记录交易流水==========");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
