package com.rainsensor.rainsensorrestapi.service;

import com.rainsensor.rainsensorrestapi.models.Measurements;
import com.rainsensor.rainsensorrestapi.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

    public void saveMeasurement(Measurements measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public List<Measurements> findRainyDays(Boolean b) {
        return measurementsRepository.findAllByRaining(b);
    }

    public void enrichMeasurement(Measurements measurements) {
        measurements.setLocalDateTime(LocalDateTime.now());
        measurements.setSensor(sensorService.findByName(measurements.getSensor().getName()));
    }

}
