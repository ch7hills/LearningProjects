package com.hills.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

import com.hills.kafka.models.Vehicle;
import com.hills.kafka.repo.VehicleRepository;

public class VehicleController {

  @Autowired
  private VehicleRepository repository;

  private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

  @KafkaListener(topics = "${kafka.topic.vehicle.request}", containerFactory = "requestReplyListenerContainerFactory")
  @SendTo()
  public Vehicle receive(String vin) {
    LOGGER.info("received request for VIN {} ", vin);
    Vehicle Vehicle = repository.getVehicle(vin);
    LOGGER.info("sending reply {} ", Vehicle);
    return Vehicle;
  }
}
