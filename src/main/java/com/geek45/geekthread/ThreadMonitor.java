/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadMonitor
 * @Decription: 线程监控
 * @Author: rubik
 *  rubik create ThreadMonitor.java of 2022/1/20 11:08 下午
 */
public class  ThreadMonitor {
    private static final Logger logger = LoggerFactory.getLogger(ThreadMonitor.class);

    /**
     * 监控线程，线程执行期间都应该在这里被监控到
     */
    private static Map<String, Object> map = new ConcurrentHashMap<>(16);

    /**
     * 线程池状态。线程池执行期间，又被监控的需要，应该都在这里被监控
     */
    private static Map<String, ThreadExecutor> monitorMap = new ConcurrentHashMap<>();

    /**
     * 打印线程池详情
     * @param key
     */
    public static void printThreadInfo(String key) {
        ThreadExecutor executor = monitorMap.get(key);
        ThreadPoolExecutor poolExecutor = executor.getPool();
        logger.info("[{}]-->当前状态：线程数量：{}.活跃线程数量：{}.最大线程数量：{}.任务数量：{}.配置核心线程阈值：{}.配置线程数量阈值：{}.",
                key,
                poolExecutor.getPoolSize(),
                poolExecutor.getActiveCount(),
                poolExecutor.getLargestPoolSize(),
                poolExecutor.getTaskCount(),
                poolExecutor.getCorePoolSize(),
                poolExecutor.getMaximumPoolSize());
    }

    /**
     * 开始监控
     *
     * @param millis 监控间隔时间
     */
    public static void monitor(String poolName, ThreadExecutor executor, long millis) {
        if (monitorMap.containsKey(poolName)) {
            logger.info("监控已经是启动状态");
            return;
        }
        monitorMap.put(poolName, executor);
        Thread thread = new Thread(() -> {
            final String key = poolName;
            while (monitorMap.containsKey(key)) {
                printThreadInfo(key);
                sleep(millis);
            }
        });
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setName(poolName + "-monitor");
        thread.setDaemon(Boolean.TRUE);
        thread.start();
    }

    /**
     * 休眠
     * @param millis 指定休眠多久
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            logger.error("休眠异常...", e);
        }
    }

    /**
     * 通过key，移除监控
     * 移除要监控的线程池
     * @param key
     */
    public static void stopMonitor(String key) {
        if (monitorMap.containsKey(key)) {
            monitorMap.remove(key);
        }
        if (monitorMap.size() < 1) {
            logger.info("结束监控...");
        }
    }

}
