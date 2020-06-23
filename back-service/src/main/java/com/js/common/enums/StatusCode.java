package com.js.common.enums;

/**
 * @author: 姜爽
 * @date: 2019/12/12 15:16
 * @Description: 返回枚举类
 * @version: V1.0
 */
public enum StatusCode {

    /**
     * 操作成功时返回的code码
     **/
    SUCCESS(0, "操作成功"),
    /**
     * 操作失败时返回的code码
     */
    FAIL(-1, "操作失败"),
    /**
     * 当出现参数异常时返回的code码
     */
    INVALIDPARAMS(201, "非法的参数!"),
    /**
     * 当用户未登录时返回的code码
     */
    USERNOTLOGIN(202, "用户没登录"),

    /**
     * 用户名或者密码错误
     **/
    UNKNOW_USER(203, "用户名或者密码错误"),

    /**
     * 系统异常
     **/
    SYSTEM_EXCEPTION(500, "系统出现异常");

    /**
     * 返回前端的code码
     */
    private Integer code;
    /**
     * 返回前端的数据信息
     */
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Description 获取code码
     * @return java.lang.Integer
     **/
    public Integer getCode() {
        return code;
    }

    /**
     * @Description 返回的异常信息
     * @return java.lang.String
     **/
    public String getMsg() {
        return msg;
    }
}
