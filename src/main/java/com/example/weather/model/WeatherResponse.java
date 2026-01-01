package com.example.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherResponse {

  private String city;
  private String country;

  private double temperature;
  private double feelsLike;
  private int humidity;
  private int pressure;

  private String description;
  private double windSpeed;
  private int cloudiness;
  private int visibility;

  private Map apiResponse;
}
