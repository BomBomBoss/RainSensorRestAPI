package com.rainsensor.client;

import com.rainsensor.rainsensorrestapi.DTO.MeasurementResponse;
import com.rainsensor.rainsensorrestapi.DTO.MeasurementsDTO;
import com.rainsensor.rainsensorrestapi.DTO.SensorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//Class works as a client to send post/get requests to RainSensorAPI
public class Client {
    RestTemplate restTemplate = new RestTemplate();
    final String registrationUrl = "http://localhost:8080/sensors/registration";
    final String addingMeasurementsUrl = "http://localhost:8080/measurements/add";
    final String allMeasurementsUrl = "http://localhost:8080/measurements";

    //Request to register sensor
    public SensorDTO registerSensor(String name){
        SensorDTO sensorDTO = new SensorDTO(name);
        HttpEntity<SensorDTO> request = new HttpEntity<>(sensorDTO);

        String response = restTemplate.postForObject(registrationUrl,request,String.class);
        System.out.println(response + "Sensor was registered");
        return sensorDTO;

    }
    //Request for adding 1000 measurements
    public void sendRequest(SensorDTO sensorDTO){
        MeasurementsDTO measurementsDTO = new MeasurementsDTO(sensorDTO);
        Random random = new Random();
        HttpEntity<MeasurementsDTO> request;
        System.out.println(measurementsDTO.getSensor().getName());
        for(int i = 0; i<1000; i++) {
            measurementsDTO.setValue(ThreadLocalRandom.current().nextDouble(-100,100));
            measurementsDTO.setRaining(random.nextBoolean());
            request = new HttpEntity<>(measurementsDTO);
            restTemplate.postForObject(addingMeasurementsUrl,request,String.class);
            System.out.println("Measurement was successfully added");
        }
    }

    //Request to receive all measurements
    public MeasurementResponse getMeasurements(){
        return restTemplate.getForObject(allMeasurementsUrl, MeasurementResponse.class);
    }

    public static void main(String[] args) {

        Client client = new Client();
        SensorDTO sensor =  client.registerSensor("Test Rain Sensor");
        client.sendRequest(sensor);
        System.out.println(client.getMeasurements().getMeasurementsList());
    }
}
