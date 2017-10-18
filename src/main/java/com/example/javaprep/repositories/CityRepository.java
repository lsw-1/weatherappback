package com.example.javaprep.repositories;

import com.example.javaprep.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City, String> {
    List<City> findByLocationContaining(@Param("location") String location);
}
