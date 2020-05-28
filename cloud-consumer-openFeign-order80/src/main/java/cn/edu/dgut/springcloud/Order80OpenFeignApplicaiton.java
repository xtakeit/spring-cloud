package cn.edu.dgut.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Order80OpenFeignApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(Order80OpenFeignApplicaiton.class, args);
    }
}
