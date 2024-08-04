package com.ksyun.zwq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @RestController
    public static class TestController{
        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private DiscoveryClient discoveryClient;

        @RequestMapping(value="echo/{str}", method= RequestMethod.GET)
        public String echo(@PathVariable String str) {
            return restTemplate.getForObject("http://service-provider/echo/"+str, String.class);
        }

    }

}
