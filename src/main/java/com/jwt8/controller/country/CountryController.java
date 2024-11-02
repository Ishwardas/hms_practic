package com.jwt8.controller.country;

import com.jwt8.entity.country.Country;
import com.jwt8.service.country.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    public ResponseEntity<List<Country>>listAllCountry(){
        List<Country> allCountry = countryService.getAllCountry();
        return new ResponseEntity<>(allCountry, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country>findCountryById(@PathVariable("id")long id){
        Country country = countryService.findCountryById(id);
        return new ResponseEntity<>(country,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Country>addCountry(@RequestBody Country country)
    {
        Country country1 = countryService.addCountry(country);
        return new ResponseEntity<>(country1,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Country>updateCountry(@PathVariable("id")long id,@RequestBody Country country)
    {
        Country country1 = countryService.updateCountry(id, country);
        return new ResponseEntity<>(country1,HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteCountry(@RequestParam("id")long id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country Deleted Successfully",HttpStatus.OK);
    }

}
