/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread;

import com.geek45.geekthread.config.ThreadConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BeanLoader
 * @Decription:
 * @Author: rubik
 *  rubik create BeanLoader.java of 2022/1/25 5:31 下午
 */
@Component
public class BeanLoader {
    private ThreadConfiguration configuration;
    public static final String poolName = "123";
    public static final String poolName2 = "1234";

    @Bean(poolName)
    public ThreadExecutor executor() {
        ThreadConfiguration config = configuration.clone();
        config.setEnable(Boolean.TRUE);
        return ThreadExecutor.init(poolName, config);
    }
    @Bean(poolName2)
    public ThreadExecutor executor2() {
        ThreadConfiguration config = configuration.clone();
        config.setEnable(Boolean.TRUE);
        return ThreadExecutor.init(poolName2, config);
    }

    @Autowired
    public void init(ThreadConfiguration configuration) {
        this.configuration = configuration;
    }
}
