package com.wit.rest.service;

import com.wit.rest.client.RPCClient;
import com.wit.rest.exception.CalculatorServiceProcessingException;
import com.wit.rest.model.response.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculatorRestService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorRestService.class);
    @Autowired
    private RPCClient rpcClient;

    public CalculatorResponse send(BigDecimal a, BigDecimal b, String mathematicalOperation) {
        String message = String.format("%s,%s,%s", a, b, mathematicalOperation);
        String response;
        try {
            response = rpcClient.call(message);
        }
        catch (Exception e) {
            logger.error("Error when calling the client core method.", e);
            throw new CalculatorServiceProcessingException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown",
                    "Calculation could not be processed.");
        }

        if ( response.startsWith("ERROR") ) {
            String[] split = getSplit(response);
            throw new CalculatorServiceProcessingException(HttpStatus.BAD_REQUEST, split[1], split[0]);
        }


        return unpackResponse(response); //new CalculatorResponse(response);
    }

    private CalculatorResponse unpackResponse(String response) {
        String[] splitResponse = getSplit(response);
        return new CalculatorResponse(splitResponse[1], splitResponse[0]);

    }

    private String[] getSplit(String response) {
        return response.split("!!");
    }

}
