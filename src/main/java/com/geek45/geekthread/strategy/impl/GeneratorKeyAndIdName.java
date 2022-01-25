/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.strategy.impl;

import com.geek45.geekthread.enums.GeneratorNameStrategy;
import com.geek45.geekthread.strategy.GeneratorThreadNameStrategy;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: GeneratorKeyAndIdName
 * @Decription: 生成key+自增id的线程名
 * @Author: rubik
 *  rubik create GeneratorKeyAndIdName.java of 2022/1/23 11:26 上午
 */
public class GeneratorKeyAndIdName implements GeneratorThreadNameStrategy {

    private AtomicLong num = new AtomicLong(0L);
    private String key = null;

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.KEY_AND_ID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return getKey() + "-" + num.getAndIncrement();
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Boolean isReady() {
        return null != getKey();
    }

    @Override
    public void throwExceptionIfNotReady() {
        if (!isReady()) {
            throw new RuntimeException("key 不可为空");
        }
    }
}
