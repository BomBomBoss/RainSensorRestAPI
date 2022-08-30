package com.rainsensor.rainsensorrestapi.controllers;

import com.rainsensor.rainsensorrestapi.models.Sensor;
import com.rainsensor.rainsensorrestapi.service.SensorService;
import com.rainsensor.rainsensorrestapi.util.ErrorResponse;
import com.rainsensor.rainsensorrestapi.util.SensorNotCreatedException;
import com.rainsensor.rainsensorrestapi.util.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid Sensor sensor, BindingResult bindingResult){

        sensorValidator.validate(sensor,bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for(FieldError error : errorList) {
                sb.append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(sb.toString());
        }
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
