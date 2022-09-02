package com.rainsensor.rainsensorrestapi.repository;

import com.rainsensor.rainsensorrestapi.models.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

    List<Measurements> findAllByRaining(Boolean b);

}
