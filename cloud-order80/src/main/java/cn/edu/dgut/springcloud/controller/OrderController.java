package cn.edu.dgut.springcloud.controller;

import cn.edu.dgut.springcloud.common.JsonResult;
import cn.edu.dgut.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment/consumer/save")
    public JsonResult comsume(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, JsonResult.class);
    }


}
