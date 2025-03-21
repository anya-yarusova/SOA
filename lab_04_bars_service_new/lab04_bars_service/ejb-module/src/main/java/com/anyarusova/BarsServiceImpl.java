package com.anyarusova;

import com.anyarusova.client.LabWorkClient;
import com.anyarusova.dto.LabWorkData;
import com.anyarusova.dto.Response;
import com.anyarusova.interceptor.ExtendedException;
import com.anyarusova.service.model.LabWork;

import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


@Stateless
@WebService(name = "BarsService", serviceName = "BarsService")
@SOAPBinding
public class BarsServiceImpl implements BarsService {

    private static final LabWorkClient labWorkClient = new LabWorkClient();
    private final ModelMapper mapper = new ModelMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @WebMethod(operationName = "IncreaseDifficulty")
    @Override
    public Response increaseDifficulty(@WebParam(name = "id") long id, @WebParam(name = "stepsCount") int stepsCount) {
        return processDifficultyChange(id, stepsCount, true);
    }

    @WebMethod(operationName = "DecreaseDifficulty")
    @Override
    public Response decreaseDifficulty(@WebParam(name = "id")long id, @WebParam(name = "stepsCount") int stepsCount) {
        return processDifficultyChange(id, stepsCount, false);
    }

    private Response processDifficultyChange(
            long id,
            int stepsCount,
            boolean increase) {
        
        try {
            if (id < 0 || stepsCount < 0) {
                return new Response(jakarta.ws.rs.core.Response.Status.BAD_REQUEST.getStatusCode(), "{}");
            }

            LabWorkData labWorkData = labWorkClient.getLabWorkById(id);
            LabWork labWork = mapper.map(labWorkData, LabWork.class);

            if (increase) {
                labWork.increaseDifficulty(stepsCount);
            } else {
                labWork.decreaseDifficulty(stepsCount);
            }

            LabWorkData updatedLabWorkData = mapper.map(labWork, LabWorkData.class);
            labWorkClient.updateLabWork(id, updatedLabWorkData);

            return new Response(jakarta.ws.rs.core.Response.Status.OK.getStatusCode(), objectMapper.writeValueAsString(updatedLabWorkData));
        } catch (Exception e) {
           return new Response(jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "{}");
        }
    }
}
