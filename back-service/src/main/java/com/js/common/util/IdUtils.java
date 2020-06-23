package com.js.common.util;

import java.util.UUID;

/**
 * @Author jiangshuang 分布式id生成器
 */
public class IdUtils {
    private IdUtils() {
        throw new IllegalStateException("IdUtils工具异常");
    }

    public static synchronized String get32Uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
