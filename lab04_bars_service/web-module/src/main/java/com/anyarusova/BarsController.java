package com.anyarusova;

import com.anyarusova.interceptor.ExtendedException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.ejb.EJB;

@Path("/bars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BarsController {

    @EJB(lookup = "java:global/web-module-1.0-SNAPSHOT/BarsServiceImpl!com.anyarusova.BarsService")
    private BarsService barsService;

    @POST
    @Path("/labwork/{labwork-id}/difficulty/increase/{steps-count}")
    public Response increaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        com.anyarusova.dto.Response labWork = barsService.increaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }

    @POST
    @Path("/labwork/{labwork-id}/difficulty/decrease/{steps-count}")
    public Response decreaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        com.anyarusova.dto.Response labWork = barsService.decreaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }
}
