package com.rainsensor.rainsensorrestapi.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {


    @NotEmpty(message = "Please enter sensor's name")
    @Size(min = 3, max = 30, message = "Sensor's name should be between 3 and 30 letters")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorDTO() {
    }
}
