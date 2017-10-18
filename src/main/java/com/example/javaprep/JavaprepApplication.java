package com.example.javaprep;

import com.example.javaprep.models.City;
import com.example.javaprep.models.enums.UnitFormat;
import com.example.javaprep.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JavaprepApplication {

    private static final Logger log = LoggerFactory.getLogger(JavaprepApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JavaprepApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner runner(CityRepository repository) {
        return (args) -> {
            repository.save(new City("stockholm", UnitFormat.CELSIUS));
            repository.save(new City("london", UnitFormat.FAHRENHEIT));
            repository.save(new City("oslo", UnitFormat.CELSIUS));
            repository.save(new City("manchester", UnitFormat.FAHRENHEIT));
            log.info("cities found with findAll():");
            log.info("-------------------------------");
            for (City city : repository.findAll()) {
                log.info(city.toString());
            }
        };
    }
}
