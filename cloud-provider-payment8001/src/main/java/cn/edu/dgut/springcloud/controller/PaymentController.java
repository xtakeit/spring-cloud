package cn.edu.dgut.springcloud.controller;

import cn.edu.dgut.springcloud.common.JsonResult;
import cn.edu.dgut.springcloud.entities.Payment;
import cn.edu.dgut.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/save")
    public JsonResult save(@RequestBody Payment payment) {
        System.out.println(payment.getSerial());
        paymentService.save(payment);
        if (payment != null) {
            return JsonResult.ok(serverPort);
        }
        return JsonResult.errorMsg("插入数据库失败" + serverPort);
    }

    @GetMapping("/payment/get/{id}")
    public JsonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return JsonResult.build(200, serverPort, payment);
        }
        return JsonResult.errorMsg("没有该记录");
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.stream().forEach(instance -> {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        });
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String port(){
       return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
