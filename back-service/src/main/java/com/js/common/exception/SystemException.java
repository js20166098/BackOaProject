package com.js.common.exception;

/**
 * @Author: 姜爽
 * @Description: 系统异常类定义
 * @Date: 2020/5/4 10:19
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SystemException(String message) {
        super(message);
    }
}
