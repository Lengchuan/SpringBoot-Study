package com.lc.springBoot.jpa.config.ReadAndWrite.annotation;

import java.lang.annotation.*;

/**
 * 切换数据源
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String dataSource() default "";//数据源
}
