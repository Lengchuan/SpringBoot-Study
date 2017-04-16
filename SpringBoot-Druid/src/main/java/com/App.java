package com;

import com.lc.springBoot.druid.model.Class;
import com.lc.springBoot.druid.model.Student;
import com.lc.springBoot.druid.service.ClassService;
import com.lc.springBoot.druid.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Date;

/**
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-4-4
 */
@SpringBootApplication
@ServletComponentScan("com.lc.springBoot.dataSource.monitor")//扫描servlet配置
public class App implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    public void run(String... args) throws Exception {
        Student student = new Student();
        student.setAge(1);
        student.setBirthday(new Date());
        student.setEmail("123@123.com");
        student.setName("test");
        //studentService.createUser(student);
        //studentService.getByPage(1,2);

        //
        Class c = new Class();
        c.setClassName("test");
        //classService.createClass(c);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
