package com.geek45.geekthread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest(classes = GeekThreadApplication.class)
class GeekThreadApplicationTests {

    @Resource
    @Qualifier(BeanLoader.poolName)
    private ThreadExecutor executor;
    @Resource
    @Qualifier(BeanLoader.poolName2)
    private ThreadExecutor executor2;

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition stop = lock.newCondition();

    @Test
    @DisplayName("测试线程池")
    void contextLoads() {
        executor.executor(() -> {
            System.err.println("okokok");
        });

        executor2.executor(() -> System.out.println("fdsfads"));

        try {
            lock.lock();
            stop.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
