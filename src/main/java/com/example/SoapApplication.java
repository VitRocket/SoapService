package com.example;

import com.example.dao.domain.Product;
import com.example.dao.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class SoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapApplication.class, args);
    }

}

@RestController
class ServiceInstanceRestController {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ServiceInstanceRestController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/actuator/info")
    public String actuatorInfo() {
        return "Hello I am soapservice!";
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}

@Slf4j
@Component
@RequiredArgsConstructor
class LoadData {

    private final ProductService productService;

    @PostConstruct
    private void init() throws SQLException {
        log.info("========== Start Init data in to database ==========");
        log.info(productService.addProduct(new Product("Table", "Red", "Good Factory")).toString());
        log.info(productService.addProduct(new Product("Chair", "White", "Hello")).toString());
        log.info("========== Finish Init data in to database ==========");
    }
}
