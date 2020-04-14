import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是一个错误的使用方法,项目是无法启动的，启动类不能直接放在默认的包路径下
 *
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-3-26
 */
@SpringBootApplication
@RestController
public class HelloWorldAppTest1 {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("/")
    public String index() {
        return "this is index page";
    }

    /**
     * 用于启动项目
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldAppTest1.class, args);
    }

}
