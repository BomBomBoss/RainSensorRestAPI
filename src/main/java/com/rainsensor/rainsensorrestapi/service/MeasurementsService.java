package com.rainsensor.rainsensorrestapi.service;

import com.rainsensor.rainsensorrestapi.models.Measurements;
import com.rainsensor.rainsensorrestapi.repository.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public void saveMeasurement(Measurements measurement) {
        measurementsRepository.save(measurement);
    }
}
