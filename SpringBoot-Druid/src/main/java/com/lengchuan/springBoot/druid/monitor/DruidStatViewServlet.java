package com.lengchuan.springBoot.druid.monitor;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 配置一个servlet,让我们可以访问监控页面
 *
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-7
 */
//表明这是一个servlet
@WebServlet(urlPatterns = "/dataSource/*",//通过哪个url访问
        initParams = {
                @WebInitParam(name = "loginUsername", value = "lengchuan"),//用户名
                @WebInitParam(name = "loginPassword", value = "123456"), //密码
                @WebInitParam(name = "resetEnable", value = "true"),//是否可以重置
                // @WebInitParam(name = "allow",value = "127.0.0.1"),//白名单
                // @WebInitParam(name = "deny",value = "192.168.1.1")//黑名单
        })
public class DruidStatViewServlet extends StatViewServlet {
}
