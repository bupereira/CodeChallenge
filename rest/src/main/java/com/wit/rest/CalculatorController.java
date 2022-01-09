package com.wit.rest;

import com.wit.rest.model.response.CalculatorResponse;
import com.wit.rest.service.CalculatorRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {

    @Autowired
    CalculatorRestService calculatorRestService;

    @RequestMapping("/sum")
    public ResponseEntity<CalculatorResponse> sum(
            @RequestParam(value="x") BigDecimal x, @RequestParam(value="y") BigDecimal y) {
        return trigger(x, y, "Sum");
    }

    @RequestMapping("/subtract")
    public ResponseEntity<CalculatorResponse> subtract(
            @RequestParam(value="x") BigDecimal x, @RequestParam(value="y") BigDecimal y) {
        return trigger(x, y, "Subtract");
    }

    @RequestMapping("/multiply")
    public ResponseEntity<CalculatorResponse> multiply(
            @RequestParam(value="x") BigDecimal x, @RequestParam(value="y") BigDecimal y) {
        return trigger(x, y, "Multiply");
    }

    @RequestMapping("/divide")
    public ResponseEntity<CalculatorResponse> divide(
            @RequestParam(value="x") BigDecimal x, @RequestParam(value="y") BigDecimal y) {
        return trigger(x, y, "Divide");
    }

    private ResponseEntity<CalculatorResponse> trigger(BigDecimal x, BigDecimal y, String mathematicalOperation) {
        CalculatorResponse calculatorResponse = calculatorRestService.send(x, y, mathematicalOperation);
        return new ResponseEntity<CalculatorResponse>(calculatorResponse, HttpStatus.OK);
    }
}
