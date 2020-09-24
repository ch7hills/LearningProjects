package com.hills.kafka.repo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.hills.kafka.models.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

  Map<String, Vehicle> Vehicles = new HashMap<>();

  @PostConstruct
  private void initVehicles() {
    Vehicles.put("123", new Vehicle("123", "ABC123"));
    Vehicles.put("987", new Vehicle("987", "ZYX987"));
  }

  @Override
  public Vehicle getVehicle(String vin) {
    return Vehicles.get(vin);
  }

}
