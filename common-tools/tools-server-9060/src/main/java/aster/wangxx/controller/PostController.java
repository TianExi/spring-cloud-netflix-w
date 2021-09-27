package aster.wangxx.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author By wangxx
 * @Date 10:31 2021/9/24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/threads")
@Slf4j
public class PostController {

    @Autowired
    private ThreadPoolExecutor commonUsualPoolExecutor;

    @Autowired
    private Map commonUsualHashMap;

    private AtomicInteger num = new AtomicInteger(0);

    @PostMapping("/getMessage")
    public String getMessage (@RequestBody JSONObject json, @RequestHeader HttpHeaders httpHeaders) {
        return "hello world";
    }

    @PostMapping("/returnSubmitWaiting")
    public Object returnSubmitWaiting () throws ExecutionException, InterruptedException {
        Future<Object> future = commonUsualPoolExecutor.submit(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            Thread.sleep(3000);
            return "hello world";
        });
        return future.get();
    }

    @PostMapping("/returnSubmitNoWaiting")
    public Object returnSubmitNoWaiting () throws ExecutionException, InterruptedException {
        Future<Object> future = commonUsualPoolExecutor.submit(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            return "hello world";
        });
        return future.get();
    }

    @PostMapping("/returnSubmitNoWaitingWrong")
    public Object returnSubmitNoWaitingWrong () {
        Future<Object> future = commonUsualPoolExecutor.submit(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            return "hello world" + 1/0;
        });
        Map result = new HashMap();
        try {
            result.put("Data", future.get());
            result.put("Code", "0000");
        } catch (InterruptedException e) {
            result.put("Data", e.getMessage());
            result.put("Code", "9999");
            log.error("信息提交错误：Interrupted，" + e.getMessage());
        } catch (ExecutionException e) {
            result.put("Data", e.getMessage());
            result.put("Code", "9999");
            log.error("信息提交错误：Execution，" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/returnExecuteNoWaitingWrong")
    public Object returnExecuteNoWaitingWrong () {
        commonUsualPoolExecutor.execute(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            int a = 1/0;
        });
        return "成功";
    }

    @PostMapping("/getConcurrentValue")
    public Object concurrentHashMap (@RequestBody JSONObject json) {
        commonUsualHashMap.put(num.getAndIncrement(), System.currentTimeMillis());
        log.info("当前公共Map里的信息为：" + commonUsualHashMap);
        return commonUsualHashMap.size();
    }
}
