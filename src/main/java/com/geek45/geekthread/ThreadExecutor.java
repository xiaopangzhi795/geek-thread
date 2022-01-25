/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread;

import com.geek45.geekthread.config.ThreadConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadExecutor
 * @Decription: 线程执行器
 * @Author: rubik
 * rubik create ThreadExecutor.java of 2022/1/20 11:03 下午
 */
public class ThreadExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadExecutor.class);

    private ThreadPoolExecutor poolExecutor = null;

    public static ThreadExecutor init(String key, ThreadConfiguration configuration) {
        ThreadExecutor threadExecutor = new ThreadExecutor();
        logger.info("线程池的配置是：{}", configuration);
        threadExecutor.poolExecutor = new ThreadPoolExecutor(configuration.getCoreSize(),
                configuration.getMaxSize(),
                configuration.getKeepAliveTime(),
                TimeUnit.valueOf(configuration.getTimeUnit()),
                new LinkedBlockingDeque<>(configuration.getTaskMaxCount()),
                GeekThreadFactory.create(configuration));
        if (null != configuration.getMonitor() && configuration.getMonitor()) {
            ThreadMonitor.monitor(key, threadExecutor, configuration.getMonitorMills());
        }
        Runtime.getRuntime().addShutdownHook(threadExecutor.poolExecutor.getThreadFactory().newThread((() -> {
            final String poolName = key;
            logger.info("关闭线程池,关闭监控并移除监控...");
            ThreadMonitor.stopMonitor(poolName);
        })));
        return threadExecutor;
    }

    public void executor(Runnable task) {
        poolExecutor.execute(task);
    }

    public Thread newThread(Runnable task) {
        return poolExecutor.getThreadFactory().newThread(task);
    }

    protected ThreadPoolExecutor getPool() {
        return this.poolExecutor;
    }

}
