package cn.edu.dgut.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentZK8004Application {
    public static void main(String[] args) {
        SpringApplication.run(PaymentZK8004Application.class, args);
    }
}
