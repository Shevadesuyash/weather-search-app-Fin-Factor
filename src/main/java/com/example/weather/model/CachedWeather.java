package com.example.weather.model;

import java.time.Instant;
import lombok.Data;

@Data
public class CachedWeather {

  private WeatherResponse weatherResponse;
  private Instant timestamp;

  public CachedWeather(WeatherResponse weatherResponse) {
    this.weatherResponse = weatherResponse;
    this.timestamp = Instant.now();
  }

  public boolean isExpired(long expirySeconds) {
    return Instant.now().isAfter(timestamp.plusSeconds(expirySeconds));
  }
}
