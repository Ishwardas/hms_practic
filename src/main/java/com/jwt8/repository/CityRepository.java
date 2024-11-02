package com.jwt8.repository;

import com.jwt8.entity.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City>findByCity(String city);
}