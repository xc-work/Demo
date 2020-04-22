package com.demo.swagger.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/rest")
public class UserRest {

    @ApiOperation("dd")
    @POST
    @Path("/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response name(String name, @Context HttpServletRequest request) {
        return Response.ok("rest say hello to " + name).build();
    }
}
