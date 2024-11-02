package com.jwt8.configuration;

import com.jwt8.entity.city.City;
import com.jwt8.entity.country.Country;
import com.jwt8.entity.property.Property;
import com.jwt8.payload.property.PropertyDto;
import com.jwt8.repository.CityRepository;
import com.jwt8.repository.CountryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class MapperDtoOrEntity {
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public MapperDtoOrEntity(CountryRepository countryRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }


    public PropertyDto mapToPropertyDto(Property property){
        PropertyDto propertyDto=new PropertyDto();
        propertyDto.setName(property.getName());
        propertyDto.setCity(property.getCity().getCity());
        propertyDto.setCountry(property.getCountry().getCountry());
        propertyDto.setNo_of_bathrooms(property.getNo_of_bathrooms());
        propertyDto.setNo_of_bed(property.getNo_of_bed());
        propertyDto.setNo_of_bedrooms(property.getNo_of_bedrooms());
        propertyDto.setNo_of_guest(property.getNo_of_guest());
        return propertyDto;
    }

    public Property mapToProperty(PropertyDto propertyDto){
        Property property=new Property();
        property.setName(propertyDto.getName());
        property.setNo_of_bathrooms(propertyDto.getNo_of_bathrooms());
        property.setNo_of_bed(propertyDto.getNo_of_bed());
        property.setNo_of_guest(propertyDto.getNo_of_guest());
        property.setNo_of_bedrooms(propertyDto.getNo_of_bedrooms());

        Optional<City> byCity = cityRepository.findByCity(propertyDto.getCity().toLowerCase());
        if (byCity.isPresent()){
            property.setCity(byCity.get());
        }else {
            City city = new City();
            city.setCity(propertyDto.getCity().toLowerCase());
            cityRepository.save(city);
            property.setCity(city);
        }
        Optional<Country> country = countryRepository.findByCountry(propertyDto.getCountry());
        if (country.isPresent()){
            property.setCountry(country.get());
        }else{
            Country country1=new Country();
            country1.setCountry(propertyDto.getCountry().toLowerCase());
            Country country2 = countryRepository.save(country1);
            property.setCountry(country2);
        }
return property;
    }
}
