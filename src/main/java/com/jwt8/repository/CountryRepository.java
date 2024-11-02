package com.jwt8.repository;

import com.jwt8.entity.country.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country>findByCountry(String country);
}