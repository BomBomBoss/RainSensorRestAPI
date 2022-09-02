package com.rainsensor.rainsensorrestapi.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collections;

@Entity
@Table(name = "Measurements")
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value",scale = 1, precision = 4)
    @NotNull(message = "Please enter value")
    @Range(min = -100, max = 100, message = "Temperature should be between -100 and 100")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "please note raining status")
    private boolean raining;

    @Column(name= "measurement_time")
    private LocalDateTime localDateTime;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    @NotNull(message = "please enter sensor's name")
    private Sensor sensor;

    public Measurements() {
    }

    public Measurements(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
//        sensor.setMeasurementsList(Collections.singletonList(this));
    }

}
