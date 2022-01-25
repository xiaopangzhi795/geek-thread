/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.strategy.impl;

import com.geek45.geekthread.enums.GeneratorNameStrategy;
import com.geek45.geekthread.strategy.GeneratorThreadNameStrategy;
import com.geek45.geekthread.util.UUIDUtil;

/**
 * @ClassName: GeneratorKeyAndUidName
 * @Decription: 生成key+16位uid类型的线程名
 * @Author: rubik
 * rubik create GeneratorKeyAndUidName.java of 2022/1/23 11:26 上午
 */
public class GeneratorKeyAndUidName implements GeneratorThreadNameStrategy {
    private String key = null;

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.KEY_AND_UID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return getKey() + "-" + UUIDUtil.uuidStrShort();
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
