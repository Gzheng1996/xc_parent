package com.xuecheng.manage_cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan(basePackages = {"com.xuecheng.manage_cms"})
@ComponentScan(basePackages = {"com.xuecheng.api.cms"})
@EntityScan("com.xuecheng.framework.domain.cms")
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class,args);
    }



    @Bean
    public RestTemplate getOkHttp(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

}
