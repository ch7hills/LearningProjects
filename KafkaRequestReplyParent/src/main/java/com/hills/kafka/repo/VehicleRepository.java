package com.hills.kafka.repo;

import com.hills.kafka.models.Vehicle;

public interface VehicleRepository {

  Vehicle getVehicle(String vin);

}
