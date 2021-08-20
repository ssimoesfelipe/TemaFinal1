package com.felipesimoes1.cloud.tema08;

import com.felipesimoes1.cloud.tema08.configuration.AppConfig;
import com.felipesimoes1.cloud.tema08.rxnetty.HealthCheckResource;
import com.felipesimoes1.cloud.tema08.rxnetty.RxNettyHandler;
import com.felipesimoes1.cloud.tema08.service.Calculator;
import io.reactivex.netty.RxNetty;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        HealthCheckResource healthCheck = (HealthCheckResource) applicationContext.getBean("healthCheckResource");

        RxNetty.createHttpServer(8090, new RxNettyHandler("/healthCheck",
                        new HealthCheckEndpoint(healthCheck),
                        (Calculator) applicationContext.getBean("calculator")))
                .startAndWait();
    }
}
