package com.example.weather.Service;

import com.example.weather.Exception.CityNotFoundException;
import com.example.weather.Exception.WeatherServiceException;
import com.example.weather.model.CachedWeather;
import com.example.weather.model.WeatherResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

  private final RestTemplate restTemplate;
  private final Map<String, CachedWeather> cache = new HashMap<>();

  @Value("${weather.api.base-url}")
  private String baseUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.units}")
  private String units;

  @Value("${cache.expiry.second}")
  private long cacheExpirySeconds;

  public WeatherService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public WeatherResponse getWeatherByCity(String city) throws Exception {

    String cityKey = city.toLowerCase();

    if (cache.containsKey(cityKey)) {
      log.info("Cache hit with values : " + cityKey);
      CachedWeather cached = cache.get(cityKey);
      if (!cached.isExpired(cacheExpirySeconds)) {
        return cached.getWeatherResponse();
      } else {
        log.info("Cache expired");
      }
    }
    WeatherResponse response;
    try {
      log.info("Calling Weather Api");
      response = callApiRequest(cityKey);
    } catch (CityNotFoundException | WeatherServiceException e) {
      throw e;
    } catch (Exception e) {
      throw new Exception(e);
    }
    return response;
  }

  private WeatherResponse callApiRequest(String city) throws Exception {

    String url = String.format("%s?q=%s&appid=%s&units=%s", baseUrl, city, apiKey, units);

    try {
      JsonNode response = restTemplate.getForObject(url, JsonNode.class);
      log.info("Weather API response received");

      return mapResponseToWeatherResponse(response, city);

    } catch (HttpStatusCodeException e) {

      int statusCode = e.getStatusCode().value();
      log.error("Weather API error: {}", e.getResponseBodyAsString());

      if (statusCode == 404) {
        throw new CityNotFoundException(city);
      }
      throw new WeatherServiceException(
          "Weather service is currently unavailable. Please try again later.");

    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw new WeatherServiceException("Unable to process weather request at the moment.");
    }
  }

  private WeatherResponse mapResponseToWeatherResponse(JsonNode response, String city) {
    WeatherResponse weatherResponse =
        WeatherResponse.builder()
            .city(response.path("name").asText())
            .country(response.path("sys").path("country").asText())
            .temperature(response.path("main").path("temp").asDouble())
            .feelsLike(response.path("main").path("feels_like").asDouble())
            .humidity(response.path("main").path("humidity").asInt())
            .pressure(response.path("main").path("pressure").asInt())
            .description(response.path("weather").get(0).path("description").asText())
            .windSpeed(response.path("wind").path("speed").asDouble())
            .cloudiness(response.path("clouds").path("all").asInt())
            .visibility(response.path("visibility").asInt())
            .apiResponse(response)
            .build();

    cache.put(city.toLowerCase(), new CachedWeather(weatherResponse));
    log.info("Cache updated with key : " + city);
    return weatherResponse;
  }
}
