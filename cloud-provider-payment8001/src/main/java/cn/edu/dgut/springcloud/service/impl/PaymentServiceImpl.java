package cn.edu.dgut.springcloud.service.impl;

import cn.edu.dgut.springcloud.dao.PaymentDao;
import cn.edu.dgut.springcloud.entities.Payment;
import cn.edu.dgut.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment save(Payment payment) {
       return paymentDao.save(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentDao.findById(id).orElse(null);
    }
}
