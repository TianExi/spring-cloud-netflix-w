package aster.wangxx.config;

import aster.wangxx.pools.XRejectedExecutionHandlers;
import aster.wangxx.pools.XThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolConfig
 * @Description TODO
 * @Author By wangxx
 * @Date 9:24 2021/9/26
 * @Version 1.0
 **/

@Configuration
@PropertySource("classpath:thread-config.properties")
@Slf4j
public class XThreadPoolConfig {

    @Value("${thread.pools.corePoolSize}")
    private int corePoolSize;

    @Value("${thread.pools.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${thread.pools.keepAliveTimeInSeconds}")
    private int keepAliveTimeInSeconds;

    @Value("${thread.pools.blockQueueSize}")
    private int blockQueueSize;

    @Bean
    public ThreadPoolExecutor commonUsualPoolExecutor () {
        BlockingQueue queue = new LinkedBlockingQueue(blockQueueSize);
        XThreadFactory xThreadFactory = new XThreadFactory();
        XRejectedExecutionHandlers xRejectedExecutionHandlers = new XRejectedExecutionHandlers();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTimeInSeconds, TimeUnit.SECONDS, queue, xThreadFactory, xRejectedExecutionHandlers);
        log.info("线程池创建完毕，活跃线程数：" + threadPoolExecutor.getActiveCount());
        return threadPoolExecutor;
    }

    @Bean
    public Map commonUsualHashMap () {
        return new ConcurrentHashMap();
    }
}
