package com.rainsensor.rainsensorrestapi.util;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String message) {
        super(message);
    }
}
