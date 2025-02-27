package com.anyarusova;

import com.anyarusova.dto.LabWorkData;
import com.anyarusova.interceptor.ExtendedException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.ejb.EJB;

@Path("/bars")
@Consumes(MediaType.APPLICATION_JSON)
public class BarsController {

    @EJB(lookup = "java:global/web-module-1.0-SNAPSHOT/BarsServiceImpl!com.anyarusova.BarsService")
    private BarsService barsService;

    @POST
    @Path("/labwork/{labwork-id}/difficulty/increase/{steps-count}")
    public Response increaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        validate(id, stepsCount);
        LabWorkData labWork = barsService.increaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }

    @POST
    @Path("/labwork/{labwork-id}/difficulty/decrease/{steps-count}")
    public Response decreaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        validate(id, stepsCount);
        LabWorkData labWork = barsService.decreaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }

    private void validate(long id, int stepsCount) throws ExtendedException {
        if (id < 0 || stepsCount < 0) {
            throw new ExtendedException(Response.Status.BAD_REQUEST, "Некорректный ID лабораторной работы или значение для количества шагов");
        }
    }
}
