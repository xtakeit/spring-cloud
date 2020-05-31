package cn.edu.dgut.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@EnableEurekaClient
public class Gateway9527Application {
    public static void main(String[] args) {
        SpringApplication.run(Gateway9527Application.class, args);
    }
}

/**
 * @Author EiletXie * @Since 2020/3/12 15:01
 */
@Configuration
class GatewayConfig {
    /**
     * 配置了一个id为route-name的路由规则     * 当访问地址 http://localhost:9527/guonei时会自动转发到地址： http://news.baidu.com/guonei     * @param builder     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_eiletxie", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path_route_eiletxie2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}