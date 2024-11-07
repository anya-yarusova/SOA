package com.anyarusova.lab02_bars_sevice.controller;

import com.anyarusova.lab02_bars_sevice.dto.LabWorkData;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedException;
import com.anyarusova.lab02_bars_sevice.service.BarsService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/bars")
@Consumes(MediaType.APPLICATION_JSON)
public class BarsController {

    @Inject
    BarsService barsService;

    @POST
    @Path("/labwork/{labwork-id}/difficulty/increase/{steps-count}")
    public Response increaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        LabWorkData labWork = barsService.increaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }

    @POST
    @Path("/labwork/{labwork-id}/difficulty/decrease/{steps-count}")
    public Response decreaseDifficulty(@PathParam("labwork-id") long id, @PathParam("steps-count") int stepsCount) throws ExtendedException {
        LabWorkData labWork = barsService.decreaseDifficulty(id, stepsCount);
        return Response.ok(labWork).build();
    }
}
