package com.wit.rest.controller;

import com.wit.rest.model.response.CalculatorResponse;
import com.wit.rest.service.CalculatorRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorRestService calculatorRestService;

    @RequestMapping(path="/sum", produces = {"application/json"})
    public ResponseEntity<CalculatorResponse> sum(
            @RequestParam(value="a") BigDecimal a, @RequestParam(value="b") BigDecimal b) {
        return trigger(a, b, "Sum");
    }

    @RequestMapping(path="/subtract", produces = {"application/json"})
    public ResponseEntity<CalculatorResponse> subtract(
            @RequestParam(value="a") BigDecimal a, @RequestParam(value="b") BigDecimal b) {
        return trigger(a, b, "Subtract");
    }

    @RequestMapping(path="/multiply", produces = {"application/json"})
    public ResponseEntity<CalculatorResponse> multiply(
            @RequestParam(value="a") BigDecimal a, @RequestParam(value="b") BigDecimal b) {
        return trigger(a, b, "Multiply");
    }

    @RequestMapping(path="/divide", produces = {"application/json"})
    public ResponseEntity<CalculatorResponse> divide(
            @RequestParam(value="a") BigDecimal a, @RequestParam(value="b") BigDecimal b) {
        return trigger(a, b, "Divide");
    }

    private ResponseEntity<CalculatorResponse> trigger(BigDecimal a, BigDecimal b, String mathematicalOperation) {
        CalculatorResponse calculatorResponse = calculatorRestService.send(a, b, mathematicalOperation);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(calculatorResponse, headers, HttpStatus.OK);
    }

}
