package com.example.javaprep.controllers;

import com.example.javaprep.models.City;
import com.example.javaprep.models.api.ApiCurrentWeatherResponse;
import com.example.javaprep.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cities")
public class WeatherController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${weather.api.id}")
    private String appId;
    @Value("${weather.api.url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CityRepository weatherRepository;

    @GetMapping("/{id}")
    public ApiCurrentWeatherResponse getCityById(@PathVariable("id") String id) {
        Optional<City> city = Optional.ofNullable(weatherRepository.findOne(id));
        if (city.isPresent()) {
            String formattedURL = String.format("%s?q=%s&units=%s&appid=%s",
                    baseUrl, city.get().getLocation(), city.get().getFormattedUnit(), appId);
            return restTemplate.getForObject(formattedURL, ApiCurrentWeatherResponse.class);
        }
        return null;
    }

    @GetMapping("/search/{location}")
    public ApiCurrentWeatherResponse getCityByName(@PathVariable("location") String location) {
        List<City> city = weatherRepository.findByLocationContaining(location);
        if (city.size() == 0) throw new RuntimeException();
        String formattedURL = String.format("%s?q=%s&units=%s&appid=%s",
                baseUrl, city.get(0).getLocation(), city.get(0).getFormattedUnit(), appId);
        ApiCurrentWeatherResponse response = restTemplate.getForObject(formattedURL, ApiCurrentWeatherResponse.class);
        return response;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> postCity(@RequestBody City city) {
        City createdCity;
        try {
            createdCity = weatherRepository.save(city);
        } catch (Exception e) {
            logger.error("Error saving location");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(createdCity, HttpStatus.OK);
    }

    @GetMapping
    public List<City> getAllCities() {
        return (List<City>) weatherRepository.findAll();
    }
}
