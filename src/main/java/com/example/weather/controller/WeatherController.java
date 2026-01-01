package com.example.weather.controller;

import com.example.weather.Exception.CityNotFoundException;
import com.example.weather.Exception.WeatherServiceException;
import com.example.weather.Service.WeatherService;
import com.example.weather.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/weather")
public class WeatherController {

  private final WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/")
  public String index() {
    return "redirect:/index.html";
  }

  @GetMapping()
  public ResponseEntity<?> getWeatherByCity(@RequestParam String city) {

    if (city == null || city.trim().isEmpty()) {
      return ResponseEntity.badRequest().body("City name must not be empty");
    }

    try {
      log.info(("city name : " + city));
      WeatherResponse response = weatherService.getWeatherByCity(city.trim());
      return ResponseEntity.ok(response);
    } catch (CityNotFoundException | WeatherServiceException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body("Unable to process weather request at the moment.");
    }
  }
}
