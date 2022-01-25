/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.geekthread.util;

import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @Decription:
 * @Author: rubik
 *  rubik create UUIDUtil.java of 2022/1/25 2:24 下午
 */
public class UUIDUtil {

    /**
     * 32位uid
     * @return
     */
    public static String uuidStr() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 取后16位uid
     * @return
     */
    public static String uuidStrShort() {
        return uuidStr().substring(16, 32);
    }

}
