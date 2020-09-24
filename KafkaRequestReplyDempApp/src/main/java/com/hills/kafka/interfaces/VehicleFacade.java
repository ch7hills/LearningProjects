package com.hills.kafka.interfaces;

import java.util.concurrent.CompletableFuture;

import com.hills.kafka.models.Vehicle;

public interface VehicleFacade {

  Vehicle getVehicle(String vin);

  CompletableFuture<Vehicle> getVehicleAsync(String vin);

}