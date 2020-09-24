package com.hills.kafka.serviceImpl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hills.kafka.interfaces.VehicleFacade;
import com.hills.kafka.interfaces.CompletableFutureReplyingKafkaOperations;
import com.hills.kafka.models.Vehicle;

@Component
public class VehicleFacadeImpl implements VehicleFacade {

	@Autowired
	private CompletableFutureReplyingKafkaOperations<String, String, Vehicle> requestReplyKafkaTemplate;

	@Value("${kafka.topic.vehicle.request}")
	private String requestTopic;

	@Override
	public Vehicle getVehicle(String vin) {
		try {
			return getVehicleAsync(vin).get();
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Failed to get Vehicle", e);
		}
	}

	@Override
	public CompletableFuture<Vehicle> getVehicleAsync(String vin) {
		return requestReplyKafkaTemplate.requestReply(requestTopic, vin);
	}

}
