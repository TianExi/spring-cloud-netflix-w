package aster.wangxx.pools;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName XRejectedExecutionHandlers
 * @Description TODO 线程池拒绝策略
 * @Author By wangxx
 * @Date 11:26 2021/9/24
 * @Version 1.0
 **/
@Slf4j
public class XRejectedExecutionHandlers implements RejectedExecutionHandler {

    public XRejectedExecutionHandlers () {

    }

    /**
     * 自定义线程池拒绝策略
     * @param r 执行线程
     * @param executor 线程池
     */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("线程池拒绝任务：" + r.toString());
    }
}
