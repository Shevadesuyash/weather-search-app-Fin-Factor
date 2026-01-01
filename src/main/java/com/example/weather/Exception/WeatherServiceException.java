package com.example.weather.Exception;

public class WeatherServiceException extends RuntimeException {
  public WeatherServiceException(String message) {
    super(message);
  }
}
