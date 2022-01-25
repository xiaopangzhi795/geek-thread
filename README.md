# 关于线程池的用法说明
## TODO
- [ ] 默认的一个线程池，直接注入到容器中，通过enable字段来配置是否需要默认线程池


## 配置说明
### geek.config.thread.coreSize
- 核心线程数量 .
- 默认配置：8

### geek.config.thread.maxSize
- 最大线程数 .
- 128

### geek.config.thread.keepAliveTime
- 闲置线程持续多久 .
- 默认配置：0

### geek.config.thread.timeUnit
- 时间单位 .
- 默认配置：SECONDS
- 可选值：NANOSECONDS(纳秒)MICROSECONDS(微秒)MILLISECONDS(毫秒)SECONDS(秒)MINUTES(分钟)HOURS(小时)DAYS(天)


### geek.config.thread.key
- 生成线程名时所使用的的key .
- 默认配置：thread

### geek.config.thread.generatorNameType
- 生成线程名的策略 .
- 默认配置：SHORT_UID
- 可选值：LONG_UID(32位UUID)SHORT_UID(16位UUID)KEY_AND_UID(KEY+16位UUID)KEY_AND_ID(KEY+自增id)

### geek.config.thread.taskMaxCount
- 最大支持的任务数量 .
- 默认配置：2048

### geek.config.thread.daemon
- 是否启用守护线程 .
- 默认配置：true

### geek.config.thread.priority
- 线程优先级 .
- 默认配置：5

### geek.config.thread.monitor
- 是否监控线程池状态 .
- 默认配置：true

### geek.config.thread.monitorMills
- 监控间隔时间 .
- 默认配置：3000

### geek.config.thread.enable
- 是否启用线程池
- 默认配置：false

## 使用说明

### 通过bean的方式注入
- 通过bean加载，注入一个线程池
- 推荐bean名称为线程池的名字

``` java 
 
import com.geek45.geekthread.ThreadExecutor;
import com.geek45.geekthread.config.ThreadConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BeanLoader
 * @Decription:
 * @Author: rubik
 *  rubik create BeanLoader.java of 2022/1/25 5:51 下午
 */
@Component
public class BeanLoader {

    public static final String thread1 = "thread-pool-1";

    private ThreadConfiguration configuration;

    @Bean(thread1)
    public ThreadExecutor executor1() {
        ThreadConfiguration config = configuration.clone();
        config.setEnable(Boolean.TRUE);
        return ThreadExecutor.init(thread1, config);
    }

    @Autowired
    public void init(ThreadConfiguration configuration) {
        this.configuration = configuration;
    }
}
 
 ```

### 使用

```java

import com.geek45.geekthread.ThreadExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class GeekStarterRunDemoApplicationTests {

	@Resource
	@Qualifier(BeanLoader.thread1)
	private ThreadExecutor executor;

	@Test
	void contextLoads() {
		executor.executor(() -> System.out.println("hello thread"));
	}

}


```

