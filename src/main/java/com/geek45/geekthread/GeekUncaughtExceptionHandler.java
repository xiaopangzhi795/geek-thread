/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: GeekUncaughtExceptionHandler
 * @Decription:
 * @Author: rubik
 * rubik create GeekUncaughtExceptionHandler.java of 2022/1/20 11:18 下午
 */
public class GeekUncaughtExceptionHandler<R extends DefaultThreadCallback> implements Thread.UncaughtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GeekUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.error("执行异常。。开始回调.{}", t.getName(), e);
        R.exception(e);
    }


}
