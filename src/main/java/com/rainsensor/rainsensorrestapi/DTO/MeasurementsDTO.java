package com.rainsensor.rainsensorrestapi.DTO;

import com.rainsensor.rainsensorrestapi.models.Sensor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementsDTO {


    @Min(value = -100, message = "Temperature should be between -100 and 100")
    @Max(value = 100, message = "Temperature should be between -100 and 100")
    private double value;

    @NotNull(message = "please note raining status")
    private boolean raining;

    @NotNull(message = "please enter sensor's name")
    private Sensor sensor;

    public MeasurementsDTO() {
    }

    public MeasurementsDTO(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
