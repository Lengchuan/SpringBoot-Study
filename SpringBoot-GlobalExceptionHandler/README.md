# SpingBoot全局异常处理

## @controllerAdvice()注解
+ 1.@ControllerAdvice()注解可以定义一个统一的异常处理类,我们可以定义多个统一异常处理类,
    但这里我们需要注意一点,默认的@ControllerAdvice()会处理所有的controller层抛出的异常,
    如果我们需要对不同的包进行不同的异常处理,比如pc端我们要返回一个jsp页面,app端我们需要返回
    一个json,这时候我们可以通过配置如:@ControllerAdvice("com.lc.xxx")或者
    @ControllerAdvice(basePackages="com.lc1.xxx,com.lc2.xxx")来对不同包的异常进行处理.
 
## @ExceptionHandler()注解
+ 1.@ExceptionHandler()注解用在方法上,用来处理具体的某一中或某一类异常,比如@ExceptionHandler(Exception.class)
    将会处理所有的异常,如果魔门有一个自定的异常MyException继承自Exception,同时配置了@ExceptionHandler(Exception.class)
    和@ExceptionHandler(MyException.class),则会根据抛出的具体异常来判断选用哪个处理器,比如,我们抛出了MyException,则会优先使用
    MyException的异常处理器.

## 异常处理器的覆盖
如果我们配置了多个@controllerAdvice(),如果有一个没有指定具体的包名,那么他会覆盖其它所有指定了包名的异常处理类,导致其它失效.
    