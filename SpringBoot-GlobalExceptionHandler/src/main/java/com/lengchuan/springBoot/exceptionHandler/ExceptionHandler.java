package com.lengchuan.springBoot.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@ControllerAdvice()
public class ExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param response
     * @param e
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public void ExceptionHandler(HttpServletResponse response, Exception e) {
        System.out.println("-------全局默认异常处理-----");
        try {
            response.getOutputStream().write("全局默认异常处理".getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
