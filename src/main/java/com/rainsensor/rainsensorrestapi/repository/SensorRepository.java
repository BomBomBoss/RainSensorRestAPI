package com.rainsensor.rainsensorrestapi.repository;

import com.rainsensor.rainsensorrestapi.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
