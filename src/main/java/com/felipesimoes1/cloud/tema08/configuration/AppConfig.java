package com.felipesimoes1.cloud.tema08.configuration;

import com.felipesimoes1.cloud.tema08.operations.Operation;
import com.felipesimoes1.cloud.tema08.rxnetty.HealthCheckResource;
import com.felipesimoes1.cloud.tema08.operations.*;
import com.felipesimoes1.cloud.tema08.service.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.felipesimoes1.cloud.tema08")
public class AppConfig {

    @Bean
    public Calculator calculator(){
        return new Calculator();
    }

    @Bean
    public HealthCheckResource healthCheckResource() {
        return new HealthCheckResource();
    }

    @Bean
    public Operation addition() {
        return new Addition();
    }

    @Bean
    public Operation division() {
        return new Division();
    }

    @Bean
    public Operation multiplication() {
        return new Multiplication();
    }

    @Bean
    public Operation exponentiation() {
        return new Exponentiation();
    }

    @Bean
    public Operation subtraction() {
        return new Subtraction();
    }
}
