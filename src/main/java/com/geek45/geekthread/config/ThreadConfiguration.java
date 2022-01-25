/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.config;

import com.geek45.geekthread.enums.GeneratorNameStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadConfiguration
 * @Decription: 线程池配置
 * @Author: rubik
 * rubik create ThreadConfiguration.java of 2022/1/25 2:13 下午
 */
@ConfigurationProperties(prefix = "geek.config.thread")
public class ThreadConfiguration {

    /**
     * 是否启用线程池
     */
    private Boolean enable;
    /**
     * 核心线程数大小
     */
    private Integer coreSize;
    /**
     * 最大线程数
     */
    private Integer maxSize;
    /**
     * 闲置线程持续多久
     */
    private Long keepAliveTime;
    /**
     * 时间单位
     *
     * @see TimeUnit#name()
     */
    private String timeUnit;
    /**
     * 生成线程名时所使用的的key
     * 近当生成name类型使用到该值时才会生效
     *
     * @see GeneratorNameStrategy#name()
     */
    private String key;
    /**
     * 生成线程名的策略
     */
    private String generatorNameType;
    /**
     * 最大支持的任务数量
     */
    private Integer taskMaxCount;

    /**
     * 是否启用守护线程
     */
    private Boolean daemon;
    /**
     * 线程优先级
     */
    private Integer priority;

    /**
     * 是否监控
     */
    private Boolean monitor;

    /**
     * 监控间隔时间
     */
    private Long monitorMills;

    @Override
    public ThreadConfiguration clone() {
        ThreadConfiguration config = new ThreadConfiguration();
        config.setCoreSize(this.coreSize);
        config.setDaemon(this.daemon);
        config.setEnable(this.enable);
        config.setKey(this.key);
        config.setMonitor(this.monitor);
        config.setGeneratorNameType(this.generatorNameType);
        config.setKeepAliveTime(this.keepAliveTime);
        config.setTimeUnit(this.timeUnit);
        config.setTaskMaxCount(this.taskMaxCount);
        config.setPriority(this.priority);
        config.setMonitorMills(this.monitorMills);
        config.setMaxSize(this.maxSize);
        return config;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getCoreSize() {
        return coreSize;
    }

    public void setCoreSize(Integer coreSize) {
        this.coreSize = coreSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getGeneratorNameType() {
        return generatorNameType;
    }

    public void setGeneratorNameType(String generatorNameType) {
        this.generatorNameType = generatorNameType;
    }

    public Integer getTaskMaxCount() {
        return taskMaxCount;
    }

    public void setTaskMaxCount(Integer taskMaxCount) {
        this.taskMaxCount = taskMaxCount;
    }

    public Boolean getDaemon() {
        return daemon;
    }

    public void setDaemon(Boolean daemon) {
        this.daemon = daemon;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    public Long getMonitorMills() {
        return monitorMills;
    }

    public void setMonitorMills(Long monitorMills) {
        this.monitorMills = monitorMills;
    }
}
