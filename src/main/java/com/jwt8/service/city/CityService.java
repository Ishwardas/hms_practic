package com.jwt8.service.city;

import com.jwt8.entity.city.City;
import com.jwt8.entity.country.Country;
import com.jwt8.exception.ResourceNotFoundException;
import com.jwt8.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public List<City> getAllCity() {
        List<City> all = cityRepository.findAll();
        return all;
    }

    public City findCityById(long id) {
        City cityNotFound = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not Found"));
        return cityNotFound;
    }

    public City addCity(City city) {
        String city3 = city.getCity().toLowerCase();
        city.setCity(city3);
        City save = cityRepository.save(city);
        return save;
    }

    public City updateCity(long id, City city) {
        String city3 = city.getCity().toLowerCase();
        city.setCity(city3);
        City city1= cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not Found"));
        city1.setCity(city.getCity());
city1.setId(id);
        City save = cityRepository.save(city1);
        return save;
    }

    public void deleteCity(long id) {

        cityRepository.deleteById(id);
    }
}
