package cn.edu.dgut.springcloud.dao;

import cn.edu.dgut.springcloud.entities.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentDao extends CrudRepository<Payment, Long> {
}
