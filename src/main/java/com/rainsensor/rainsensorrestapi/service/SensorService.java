package com.rainsensor.rainsensorrestapi.service;

import com.rainsensor.rainsensorrestapi.models.Sensor;
import com.rainsensor.rainsensorrestapi.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        Optional<Sensor> found = sensorRepository.findByName(name);
        return found.orElse(null);
    }
}
