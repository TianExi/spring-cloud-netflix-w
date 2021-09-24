package wangxx.example.gatewayserver.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @description：
 * @auther: wangxx
 * @create: 2021/8/26 15:39
 * @version: 1.0
 * @main: wangxx1993ing@163.com
 */
@Slf4j
@Component
public class LocalGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("==========come in MyLogGateWayFilter:" + new Date() + "==========");
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        log.info("==========获取的用户名为：" + uname + "==========");
        if (uname == null){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        } else {
            log.info("==========开始获取请求头内容==========");
            log.info("Head：>>>>>>>>>> " + exchange.getRequest().getHeaders().toString());
            log.info("==========完成获取请求头内容==========");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
