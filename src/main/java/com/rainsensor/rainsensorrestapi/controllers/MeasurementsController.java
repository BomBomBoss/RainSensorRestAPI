package com.rainsensor.rainsensorrestapi.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.rainsensor.rainsensorrestapi.DTO.MeasurementResponse;
import com.rainsensor.rainsensorrestapi.DTO.MeasurementsDTO;
import com.rainsensor.rainsensorrestapi.models.Measurements;
import com.rainsensor.rainsensorrestapi.models.Sensor;
import com.rainsensor.rainsensorrestapi.service.MeasurementsService;
import com.rainsensor.rainsensorrestapi.service.SensorService;
import com.rainsensor.rainsensorrestapi.util.ErrorResponse;
import com.rainsensor.rainsensorrestapi.util.MeasurementsValidator;
import com.rainsensor.rainsensorrestapi.util.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final MeasurementsValidator measurementsValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementsValidator measurementsValidator, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.measurementsValidator = measurementsValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public MeasurementResponse showAllMeasurements(){
        return new MeasurementResponse((measurementsService.findAll().stream()
                .map(this::convertToMeasurementsDTO).collect(Collectors.toList())));
    }

    @GetMapping("/rainyDaysCount")
    public int showRainyDays(){
        return measurementsService.findRainyDays(true).size();

    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                     BindingResult bindingResult){

        Measurements measurements = convertToMeasurements(measurementsDTO);
        measurementsValidator.validate(measurements, bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for(FieldError error : errorList) {
                errorMsg.append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotFoundException(errorMsg.toString());
        }
        measurementsService.saveMeasurement(measurements);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(InvalidFormatException e){
        ErrorResponse response = new ErrorResponse("Please enter 'True' or 'False' ", System.currentTimeMillis());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) {
        return modelMapper.map(measurementsDTO, Measurements.class);
    }

    private MeasurementsDTO convertToMeasurementsDTO(Measurements measurements) {
        return modelMapper.map(measurements, MeasurementsDTO.class);
    }

}
