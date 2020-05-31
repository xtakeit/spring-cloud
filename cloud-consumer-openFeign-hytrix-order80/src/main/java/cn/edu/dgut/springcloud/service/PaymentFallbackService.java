package cn.edu.dgut.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "-----------fallack-ok---------";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "-----------fallback-timeout-----------";
    }
}
