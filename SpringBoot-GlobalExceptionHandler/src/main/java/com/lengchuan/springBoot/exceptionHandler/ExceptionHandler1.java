package com.lengchuan.springBoot.exceptionHandler;

import com.lengchuan.springBoot.Exception.MyException1;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
@ControllerAdvice("com.lengchuan.springBoot.controller")
public class ExceptionHandler1 {

    /**
     * 全局异常处理
     *
     * @param response
     * @param e
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public void ExceptionHandler(HttpServletResponse response, Exception e) {
        System.out.println("-------全局默认异常处理1-----");
        try {
            response.getOutputStream().write("全局默认异常处理1".getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 自定义异常处理
     *
     * @param response
     * @param e
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MyException1.class)
    public void MyExceptionHandler(HttpServletResponse response, MyException1 e) {
        System.out.println("-------自定义异常处理1-----");
        try {
            response.getOutputStream().write("自定义异常处理1".getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
