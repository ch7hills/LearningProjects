package com.hills.kafka.controllers;

import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.DeferredResult;

import com.hills.kafka.interfaces.VehicleFacade;
import com.hills.kafka.models.Vehicle;

@RestController
public class KafkaRequestReplyController {
	 @Autowired
	  private VehicleFacade VehicleFacade;

	  @GetMapping(value = "/vehicle/{vin}")
	  public DeferredResult<ResponseEntity<Vehicle>> getVehicle(@PathVariable("vin") String vin) {
	    DeferredResult<ResponseEntity<Vehicle>> result = new DeferredResult<>();
	    CompletableFuture<Vehicle> reply = VehicleFacade.getVehicleAsync(vin);
	    reply.thenAccept(Vehicle ->
	      result.setResult(new ResponseEntity<>(Vehicle, HttpStatus.OK))
	    ).exceptionally(ex -> {
	      result.setErrorResult(new ApiException(HttpStatus.NOT_FOUND, ex.getCause().getMessage()));
	      return null;
	    });
	    return result;
	  }


	  @ExceptionHandler(ApiException.class)
	  public final ResponseEntity<ErrorMessage> handleApiException(ApiException ex,
	      WebRequest request) {
	    HttpStatus status = ex.getStatus();
	    ErrorMessage errorDetails = new ErrorMessage().timestamp(ex.getTimestamp())
	        .status(status.value()).error(status.getReasonPhrase()).message(ex.getMessage());
	    if (request instanceof ServletWebRequest) {
	      ServletWebRequest servletWebRequest = (ServletWebRequest) request;
	      HttpServletRequest servletRequest =
	          servletWebRequest.getNativeRequest(HttpServletRequest.class);
	      if (servletRequest != null) {
	        errorDetails = errorDetails.path(servletRequest.getRequestURI());
	      }
	    }
	    return new ResponseEntity<>(errorDetails, status);
	  }
}
