package com.wq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by qwu on 2018/12/3.
 */
@RestController
@EnableSwagger2//使用Swagger自动生成文档 localhost:8060/swagger-ui.html
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
