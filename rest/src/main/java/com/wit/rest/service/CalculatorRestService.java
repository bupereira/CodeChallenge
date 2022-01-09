package com.wit.rest.service;

import com.wit.rest.client.RPCClient;
import com.wit.rest.model.response.CalculatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class CalculatorRestService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorRestService.class);
    @Autowired
    private RPCClient rpcClient;

    public CalculatorResponse send(BigDecimal x, BigDecimal y, String mathematicalOperation) {
        String message = String.format("%s,%s,%s", x, y, mathematicalOperation);

        String response = null;
        try {
            response = rpcClient.call(message);
        }
        catch (Exception e) {
            logger.error("Error when calling the client core method.", e);
            throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Calculation could not be processed.");
        }

        if ( response.startsWith("ERROR") )
            throw new RestException(HttpStatus.BAD_REQUEST, response);

        return new CalculatorResponse(response);
    }


}
