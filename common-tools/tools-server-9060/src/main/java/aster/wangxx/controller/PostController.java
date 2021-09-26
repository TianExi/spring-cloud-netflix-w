package aster.wangxx.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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

    @PostMapping("/getMessage")
    public String getMessage (@RequestBody JSONObject json, @RequestHeader HttpHeaders httpHeaders) {
        return "hello world";
    }

    @PostMapping("/returnTimeWaiting")
    public Object returnExecutorTimeWaiting () throws ExecutionException, InterruptedException {
        Future<Object> future = commonUsualPoolExecutor.submit(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            Thread.sleep(3000);
            return "hello world";
        });
        return future.get();
    }

    @PostMapping("/returnNoWaiting")
    public Object returnNoWaiting () throws ExecutionException, InterruptedException {
        Future<Object> future = commonUsualPoolExecutor.submit(() -> {
            log.info("执行线程方法，当前线程池活跃线程数：" + commonUsualPoolExecutor.getActiveCount());
            return "hello world";
        });
        return future.get();
    }
}
