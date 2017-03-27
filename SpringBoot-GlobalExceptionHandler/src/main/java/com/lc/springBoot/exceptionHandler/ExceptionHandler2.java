package com.lc.springBoot.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@ControllerAdvice("com.lc.springBoot.controller2")
public class ExceptionHandler2 {

    /**
     * 自定义异常处理
     *
     * @param response
     * @param e
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void ExceptionHandler(HttpServletResponse response, Exception e) {
        System.out.println("-------全局异常处理2-----");
        try {
            response.getOutputStream().write("全局异常处理2".getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
