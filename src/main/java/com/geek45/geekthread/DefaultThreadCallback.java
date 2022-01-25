/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ThreadCallback
 * @Decription:
 * @Author: rubik
 *  rubik create ThreadCallback.java of 2022/1/20 11:28 下午
 */
public class DefaultThreadCallback {
    private static final Logger logger = LoggerFactory.getLogger(DefaultThreadCallback.class);

    /**
     * 异常回调
     * @param e
     */
    public static void exception(Throwable e){
        logger.error("call exception...", e);
    }
}
