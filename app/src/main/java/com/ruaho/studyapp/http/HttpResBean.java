package com.ruaho.studyapp.http;

import com.ruaho.studyapp.bean.Bean;

import java.util.Map;

/**
 * Created by jinyunyang on 15/3/10.
 */
public class HttpResBean extends Bean {
    /** HTTP 响应状态码 **/
    private static final String HTTP_STATUS_CODE = "HTTP_STATUS_CODE";

    /** HTTP 描述信息 **/
    private static final String HTTP_MSG = "HTTP_MSG";

    /** HTTP 响应内容 **/
    private static final String HTTP_CONTENT = "HTTP_ERR_CONTENT";

    public HttpResBean(Map<Object, Object> values) {
        super(values);
    }

    public HttpResBean() {
    }

    /** HTTP 响应是否正常 **/
    public static final String HTTP_OK = "HTTP_IS_OK";

    public int getStatusCode() {
        return this.getInt(HTTP_STATUS_CODE);
    }
    public void setStatusCode(int statusCode) {
        this.set(HTTP_STATUS_CODE, statusCode);
    }

    public String getHttpContent() {
        return getStr(HTTP_CONTENT);
    }

    public void setHttpContent(String content) {
        this.set(HTTP_CONTENT, content);
    }

    public boolean isHttpOk() {
        return this.getBoolean(HTTP_OK);
    }

    public void setHttpOk(boolean isOk) {
        this.set(HTTP_OK, isOk);
    }

    public void setHttpMsg(String httpMsg) {
        this.set(HTTP_MSG, httpMsg);
    }

    public String getHttpMsg() {
        return this.getStr(HTTP_MSG);
    }
}
