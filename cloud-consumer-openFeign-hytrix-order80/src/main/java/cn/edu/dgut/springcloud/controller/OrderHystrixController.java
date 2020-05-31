package cn.edu.dgut.springcloud.controller;

import cn.edu.dgut.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@DefaultProperties(defaultFallback = "paymentInfoGlobalHandler")
@RestController
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/consumer/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfoOk(id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @GetMapping("/payment/consumer/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id) {
        //int age = 10 / 0;
        return paymentHystrixService.paymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "我是消费者80，" + "对方支付微服务(payment)有问题" + ",id=" + id;
    }


    public String paymentInfoGlobalHandler() {
        return "全局处理";
    }
}
