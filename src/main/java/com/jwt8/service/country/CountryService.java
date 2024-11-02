package com.jwt8.service.country;

import com.jwt8.entity.country.Country;
import com.jwt8.exception.ResourceNotFoundException;
import com.jwt8.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public List<Country> getAllCountry() {
        List<Country> all =
                countryRepository.findAll();
        return all;
    }

    public Country addCountry(Country country) {
        String country1 = country.getCountry();
        country.setCountry(country1);
        Country savedCountry = countryRepository.save(country);
        return savedCountry;
    }

    public Country updateCountry(long id, Country country) {
        String country1 = country.getCountry();
        country.setCountry(country1);
        Country savedCountry = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country Not Found"));
        savedCountry.setCountry(country.getCountry());
        savedCountry.setId(id);
        Country save = countryRepository.save(savedCountry);
        return save;
    }

    public void deleteCountry(long id) {
        countryRepository.deleteById(id);
    }

    public Country findCountryById(long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Country Not Found"));
        return country;
    }
}
