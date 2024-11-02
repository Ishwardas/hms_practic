package com.jwt8.controller;

import com.jwt8.entity.property.Property;
import com.jwt8.payload.property.PropertyDto;
import com.jwt8.service.property.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/getAllProperty")
     public ResponseEntity<List<Property>>getAllProperty() {
    List<Property> allProperty =
            propertyService.getAllProperty();
    return new ResponseEntity<>(allProperty, HttpStatus.OK);
}
@PostMapping("/addProperty")
    public ResponseEntity<PropertyDto>addProperty(@RequestBody PropertyDto propertyDto){
    PropertyDto propertyDto1 = propertyService.addProperty(propertyDto);
    return new ResponseEntity<>(propertyDto1,HttpStatus.CREATED);
}
@PutMapping("/updateProperty/{id}")
    public ResponseEntity<PropertyDto>updateProperty(@PathVariable("id") long id,@RequestBody PropertyDto propertyDto){
    PropertyDto propertyDto1 = propertyService.updateProperty(propertyDto, id);
    return new ResponseEntity<>(propertyDto1,HttpStatus.OK);
}

@DeleteMapping("/deleteProperty")
    public ResponseEntity<String>deleteProperty(@RequestParam("id")long id){
        propertyService.deleteProperty(id);
        return new ResponseEntity<>("Your Property Deleted Successfully",HttpStatus.OK);
}
@GetMapping("/getPropertyById/{id}")
    public ResponseEntity<PropertyDto>getPropertyById(@PathVariable("id")long id){
    PropertyDto propertyById = propertyService.getPropertyById(id);
    return new ResponseEntity<>(propertyById,HttpStatus.OK);
}

}
