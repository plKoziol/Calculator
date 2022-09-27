package pl.koziol.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.koziol.calculator.model.Unit;
import pl.koziol.calculator.service.BasicArithmeticOperationService;


@RestController
@RequestMapping("/api")
public class CalculatorController {
    @Autowired
    BasicArithmeticOperationService basicArithmeticOperationService;

    @PutMapping("/m")
    public ResponseEntity calculationInMeters(@RequestBody String inputData) {
        String result = basicArithmeticOperationService.performCalculation(inputData, Unit.M);
        if(result==null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorrect way of entering data");
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
    @PutMapping("/nm")
    public ResponseEntity calculationInNauticalMiles(@RequestBody String inputData) {
        String result = basicArithmeticOperationService.performCalculation(inputData, Unit.NM);
        if(result==null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorrect way of entering data");
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
    @PutMapping("/ft")
    public ResponseEntity calculationInFeet(@RequestBody String inputData) {
        String result = basicArithmeticOperationService.performCalculation(inputData, Unit.FT);
        if(result==null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("incorrect way of entering data");
        } else return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

}