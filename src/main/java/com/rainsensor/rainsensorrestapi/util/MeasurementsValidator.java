package com.rainsensor.rainsensorrestapi.util;

import com.rainsensor.rainsensorrestapi.models.Measurements;
import com.rainsensor.rainsensorrestapi.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.Column;

@Component
public class MeasurementsValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurements.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurements measurements = (Measurements) target;
        if(sensorService.findByName(measurements.getSensor().getName())==null){
            errors.rejectValue("sensor","", "This sensor doesn't exist");
        }
    }
}
