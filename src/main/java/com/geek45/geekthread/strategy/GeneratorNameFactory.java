/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @ClassName: GeneratorNameFacory
 * @Decription: 生成线程名字工厂
 * @Author: rubik
 *  rubik create GeneratorNameFacory.java of 2022/1/23 11:29 上午
 */
public class GeneratorNameFactory {
    private static final Logger logger = LoggerFactory.getLogger(GeneratorNameFactory.class);

    /**
     * 获取策略模式
     * @param type
     * @return
     */
    public static GeneratorThreadNameStrategy create(String type) {
        return create(type, null);
    }

    /**
     * 根据类型获取策略模式
     * @param type
     * @param key
     * @return
     */
    public static GeneratorThreadNameStrategy create(String type, String key) {
        ServiceLoader<GeneratorThreadNameStrategy> loader = ServiceLoader.load(GeneratorThreadNameStrategy.class);
        Iterator<GeneratorThreadNameStrategy> iterators = loader.iterator();
        while (iterators.hasNext()) {
            GeneratorThreadNameStrategy strategy = iterators.next();
            GeneratorThreadNameStrategy generator;
            try {
                generator = strategy.getClass().newInstance();
            } catch (InstantiationException e) {
                logger.error("初始化策略异常...InstantiationException..", e);
                throw new RuntimeException("初始化策略异常..");
            } catch (IllegalAccessException e) {
                logger.error("初始化策略异常...IllegalAccessException..", e);
                throw new RuntimeException("初始化策略异常..");
            }
            generator.setKey(key);
            if (strategy.matchType(type)) {
                generator.throwExceptionIfNotReady();
                return generator;
            }
        }
        throw new RuntimeException("未找到策略..");
    }

}
