package cn.edu.dgut.springcloud.controller;

import cn.edu.dgut.springcloud.common.JsonResult;
import cn.edu.dgut.springcloud.entities.Payment;
import cn.edu.dgut.springcloud.lb.LoadBalancer;
import com.netflix.discovery.converters.Auto;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/consumer/save")
    public JsonResult consume(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, JsonResult.class);
    }

    @GetMapping("/payment/consumer/get/{id}")
    public JsonResult get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id.toString(), JsonResult.class);
    }

    @GetMapping("/payment/consumer/lb")
    private String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }

}
