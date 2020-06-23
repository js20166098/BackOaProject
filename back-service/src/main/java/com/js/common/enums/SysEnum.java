package com.js.common.enums;

/**
 * @Author jiangshuang
 * @Description 系统属性枚举
 **/
public enum SysEnum {
    // 删除标记
    DELETE_EFFECT("0", "数据有效"), DELETE_UNEFFECT("1", "数据已删除"),
    // 是否有师父
    MASTER_HAVING("0", "有师傅"), MASTER_UNHAVING("1", "没有师父"),
    // 在职状态
    ON_JOB("0", "在职"), NOT_ON_JOB("1", "离职"), JOB_RETIRE("2", "退休"),;

    private String code;
    /**
     * 返回前端的数据信息
     */
    private String msg;

    SysEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 删除标记码
     **/
    public String getCode() {
        return code;
    }

    /**
     * 对应信息
     **/
    public String getMsg() {
        return msg;
    }
}
