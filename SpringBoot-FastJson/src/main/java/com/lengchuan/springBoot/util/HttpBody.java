package com.lengchuan.springBoot.util;

import java.io.Serializable;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-3
 */
public class HttpBody implements Serializable {
    private int status;
    private String body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
