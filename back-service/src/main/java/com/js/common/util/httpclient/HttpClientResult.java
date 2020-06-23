package com.js.common.util.httpclient;

import java.io.Serializable;

import lombok.Data;

/**
 * @version:V1.0
 * @Description:封装httpClient响应结果
 * @author:姜爽
 * @date :2020年2月15日 下午9:26:19
 */
public class HttpClientResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
