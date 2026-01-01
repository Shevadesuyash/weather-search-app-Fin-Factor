package com.example.weather.Exception;

public class CityNotFoundException extends RuntimeException {
  public CityNotFoundException(String city) {
    super("City not found: " + city);
  }
}
