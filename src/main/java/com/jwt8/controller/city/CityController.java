package com.jwt8.controller.city;

import com.jwt8.entity.city.City;
import com.jwt8.entity.country.Country;
import com.jwt8.repository.CityRepository;
import com.jwt8.service.city.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city_list")
    public ResponseEntity<List<City>> listAllCity(){
        List<City> allCity = cityService.getAllCity();
        return new ResponseEntity<>(allCity, HttpStatus.OK);
    }
    @GetMapping("/getCityById/{id}")
    public ResponseEntity<City>findCityById(@PathVariable("id")long id){
        City city = cityService.findCityById(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }
    @PostMapping("/addCity")
    public ResponseEntity<City>addCity(@RequestBody City city)
    {
        City city1 = cityService.addCity(city);
        return new ResponseEntity<>(city,HttpStatus.CREATED);
    }

    @PutMapping("/updateCity/{id}")
    public ResponseEntity<City>updateCountry(@PathVariable("id")long id,@RequestBody City city)
    {
        City city1 = cityService.updateCity(id, city);
        return new ResponseEntity<>(city1,HttpStatus.OK);
    }
    @DeleteMapping("/deleteCity")
    public ResponseEntity<String>deleteCountry(@RequestParam("id")long id){
        cityService.deleteCity(id);
        return new ResponseEntity<>("City Deleted Successfully",HttpStatus.OK);
    }
}
