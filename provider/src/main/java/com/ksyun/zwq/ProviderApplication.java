package com.ksyun.zwq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @RestController
    static class EchoController{

        @Autowired
        public Environment environment;

        @RequestMapping(value="/echo/{str}", method= RequestMethod.GET)
        public String echo(@PathVariable String str){
            //get server.port
            String port = environment.getProperty("server.port");
            return "Hello Nacos Discovery " + str + "From port: " + port;
        }
    }
}
