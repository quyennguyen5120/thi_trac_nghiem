package com.example.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TodoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://10.0.0.98:3000", "http://10.0.0.63:3000"
                                ,"http://159.223.45.168:8080", "http://localhost:3000",
                                "http://10.0.0.79:8080", "http://13.215.252.13",
                                "http://192.168.1.5:3000", "http://192.168.1.5"
                                , "http://46.137.236.193","http://46.137.236.193:80"
                                , "http://54.254.26.56",
                                "http://54.151.150.225", "http://13.213.68.228"
                                , "http://52.221.233.29", "http://54.169.201.55")
                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
            }
        };
    }
}
