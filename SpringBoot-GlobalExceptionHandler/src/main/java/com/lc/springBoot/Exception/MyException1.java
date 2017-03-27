package com.lc.springBoot.Exception;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-27
 */
public class MyException1 extends Exception {
    public MyException1() {
        super();
    }

    public MyException1(String msg) {
        super(msg);
    }
}
