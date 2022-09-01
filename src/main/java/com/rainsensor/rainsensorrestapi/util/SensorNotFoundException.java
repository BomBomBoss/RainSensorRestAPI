package com.rainsensor.rainsensorrestapi.util;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(String message) {
        super(message);
    }
}
