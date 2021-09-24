package aster.wangxx.example.configserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
class ConfigServer9070ApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        Thread s = new Thread();
        s.wait();
        s.wait(1000);

        s.sleep(1000);

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
        LinkedBlockingDeque<Runnable> workQueues = new LinkedBlockingDeque<>();
        RejectedExecutionHandler discardPolicyHandler = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor th = new ThreadPoolExecutor(4, 8, 10000, TimeUnit.SECONDS, workQueue, discardPolicyHandler);
    }

}
