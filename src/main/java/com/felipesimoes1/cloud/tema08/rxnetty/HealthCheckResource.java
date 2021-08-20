package com.felipesimoes1.cloud.tema08.rxnetty;

import netflix.karyon.health.HealthCheckHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/healthcheck")
public class HealthCheckResource implements HealthCheckHandler {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthcheck() {
        return Response.status(getStatus()).build();
    }

    @Override
    public int getStatus() {
        return 200;
    }
}
