package com.rainsensor.rainsensorrestapi.repository;

import com.rainsensor.rainsensorrestapi.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
