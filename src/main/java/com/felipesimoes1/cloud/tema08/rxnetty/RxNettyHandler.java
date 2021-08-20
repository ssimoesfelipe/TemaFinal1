package com.felipesimoes1.cloud.tema08.rxnetty;

import com.felipesimoes1.cloud.tema08.service.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import rx.Observable;

import java.util.List;
import java.util.Map;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private final HealthCheckEndpoint healthCheckEndpoint;
    private final String healthCheck;
    private final Calculator calculator;


    public RxNettyHandler(String healthCheck, HealthCheckEndpoint healthCheckEndpoint, Calculator calculator) {
        this.healthCheckEndpoint = healthCheckEndpoint;
        this.healthCheck = healthCheck;
        this.calculator = calculator;
    }

    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        Map<String, List<String>> requestParameters = request.getQueryParameters();
        if (request.getUri().startsWith(healthCheck)) {
            return healthCheckEndpoint.handle(request, response);
        }
        if (request.getUri().startsWith("/calculate")) {
            if (requestParameters.containsKey("firstnumber") && requestParameters.containsKey("operation") && requestParameters.containsKey("secondnumber")) {
                try {
                    double firstValue = Double.parseDouble(requestParameters.get("firstnumber").get(0));
                    String operation = requestParameters.get("operation").get(0);
                    double secondValue = Double.parseDouble(requestParameters.get("secondnumber").get(0));

                    response.setStatus(HttpResponseStatus.OK);
                    return response.writeStringAndFlush(calculator.doOperation(firstValue, operation, secondValue));
                } catch (NumberFormatException e) {
                    response.setStatus(HttpResponseStatus.BAD_REQUEST);
                    return response.writeStringAndFlush("BAD REQUEST! Please insert a number for operation.");
                }
            } else if(requestParameters.containsKey("history")){
                response.setStatus(HttpResponseStatus.OK);
                return response.writeStringAndFlush(String.valueOf(calculator.getHistory()));
            } else {
                response.setStatus(HttpResponseStatus.BAD_REQUEST);
                return response.writeStringAndFlush("BAD REQUEST! Please a valid URL:\n" +
                        "localhost:8080/calculate?firstnumber='num1'&operation='op'&secondnumber='num2'");
            }
        } else {
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            response.writeStringAndFlush("INTERNAL SERVER ERROR! Please insert a valid URL.");
            return response.close();
        }
    }
}