/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.strategy.impl;

import com.geek45.geekthread.enums.GeneratorNameStrategy;
import com.geek45.geekthread.strategy.GeneratorThreadNameStrategy;
import com.geek45.geekthread.util.UUIDUtil;

/**
 * @ClassName: GeneratorShortUidName
 * @Decription: 生成16位uid类型的线程名
 * @Author: rubik
 *  rubik create GeneratorShortUidName.java of 2022/1/23 11:26 上午
 */
public class GeneratorShortUidName implements GeneratorThreadNameStrategy {

    @Override
    public Boolean matchType(String type) {
        return GeneratorNameStrategy.SHORT_UID.name().equalsIgnoreCase(type);
    }

    @Override
    public String generatorName() {
        return UUIDUtil.uuidStrShort();
    }

}
