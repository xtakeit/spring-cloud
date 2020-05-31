package cn.edu.dgut.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableHystrix //客户端启动熔断器功能，内有@EnableCircuitBreaker注解
@SpringBootApplication
public class Order80OpenFeignHystrixApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(Order80OpenFeignHystrixApplicaiton.class, args);
    }
}
