package wangxx.example.gatewayserver.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @description：
 * @auther: wangxx
 * @create: 2021/8/25 20:18version
 * @version: 1.0
 * @main: wangxx1993ing@163.com
 */
@Component
public class LimitRoutePredicateFactory extends AbstractRoutePredicateFactory<LimitRoutePredicateFactory.config> {

    public LimitRoutePredicateFactory() {
        super(LimitRoutePredicateFactory.config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("version");
    }

    @Override
    public Predicate<ServerWebExchange> apply(config config) {
        return ServerWebExchange -> {
            ServerHttpRequest request = ServerWebExchange.getRequest();
            String version = request.getHeaders().get("version").get(0);
            if(!StringUtils.hasLength(version)){
                return false;
            }else {
                Integer v = Integer.parseInt(version);
                if(v >= config.getVersion()){
                    return true;
                }else {
                    return false;
                }
            }
        };
    }

    @Validated
    public static class config {
        @NotNull
        private Integer version;

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }
    }
}
