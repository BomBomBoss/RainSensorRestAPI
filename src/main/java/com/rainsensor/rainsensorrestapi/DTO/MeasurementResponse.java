package com.rainsensor.rainsensorrestapi.DTO;

import com.rainsensor.rainsensorrestapi.models.Measurements;

import java.util.List;

//Class Wrapper for list of all Measurements. To receive huge list of Measurements as one instance
public class MeasurementResponse {

    public MeasurementResponse() {
    }

    private List<MeasurementsDTO> measurementsList;

    public List<MeasurementsDTO> getMeasurementsList() {
        return measurementsList;
    }

    public void setMeasurementsList(List<MeasurementsDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }

    public MeasurementResponse(List<MeasurementsDTO> measurementsList) {
        this.measurementsList = measurementsList;
    }

    @Override
    public String toString() {
        return "MeasurementResponse{" +
                "measurementsList=" + measurementsList +
                '}';
    }
}
