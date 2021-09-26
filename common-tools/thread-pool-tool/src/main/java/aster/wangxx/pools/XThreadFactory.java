package aster.wangxx.pools;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadFactory
 * @Description TODO 自定义线程工厂
 * @Author By wangxx
 * @Date 10:56 2021/9/24
 * @Version 1.0
 **/
public class XThreadFactory implements ThreadFactory {

    /** 线程编号 */
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    /** 线程组 */
    private final ThreadGroup group;
    /** 线程名称前缀 */
    private final String namePrefix;

    public XThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        this.group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.namePrefix = "myPool-" + poolNumber.getAndIncrement() + "-MyThread-";
    }

    public Thread newThread(Runnable r) {
        // 实例化线程对象
        Thread thread = new Thread(group, r, namePrefix + poolNumber.getAndIncrement(), 0);
        return thread;
    }
}
