package com.rainsensor.rainsensorrestapi.DTO;

import com.rainsensor.rainsensorrestapi.models.Sensor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

public class MeasurementsDTO {


    @NotNull(message = "Please enter value")
    @Range(min = -100, max = 100, message = "Temperature should be between -100 and 100")
    private Double value;

    @NotNull(message = "please note raining status")
    private Boolean raining;

    @NotNull(message = "please enter sensor's name")
    private SensorDTO sensor;

    public MeasurementsDTO() {
    }

    public MeasurementsDTO(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public MeasurementsDTO(Double value, Boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public Boolean getRaining() {
        return raining;
    }

    @Override
    public String toString() {
        return "MeasurementsDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor.getName() +
                '}';
    }
}
