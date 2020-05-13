package cn.edu.dgut.springcloud.service;

import cn.edu.dgut.springcloud.entities.Payment;

public interface PaymentService {
    Payment save(Payment payment);

    Payment getById(Long id);
}
