package com.jwt8.service.property;

import com.jwt8.configuration.MapperDtoOrEntity;
import com.jwt8.entity.property.Property;
import com.jwt8.exception.ResourceNotFoundException;
import com.jwt8.payload.property.PropertyDto;
import com.jwt8.repository.PropertyRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
private MapperDtoOrEntity mapperDtoOrEntity;
    public PropertyService(PropertyRepository propertyRepository, MapperDtoOrEntity mapperDtoOrEntity) {
        this.propertyRepository = propertyRepository;

        this.mapperDtoOrEntity = mapperDtoOrEntity;
    }

    public List<Property> getAllProperty() {
        List<Property> all = propertyRepository.findAll();
        return all;
    }

    public PropertyDto addProperty(PropertyDto propertyDto) {
        Property property = mapperDtoOrEntity.mapToProperty(propertyDto);
        Property save = propertyRepository.save(property);
        PropertyDto propertyDto1 = mapperDtoOrEntity.mapToPropertyDto(save);
        return propertyDto1;
    }

    public PropertyDto updateProperty(PropertyDto propertyDto, long id) {
        Property property = mapperDtoOrEntity.mapToProperty(propertyDto);
        property.setId(id);
        Property save = propertyRepository.save(property);
        PropertyDto propertyDto1 = mapperDtoOrEntity.mapToPropertyDto(save);
        return propertyDto1;
    }

    public void deleteProperty(long id) {

        propertyRepository.deleteById(id);
    }

    public PropertyDto getPropertyById(long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property Not Found"));
        PropertyDto propertyDto = mapperDtoOrEntity.mapToPropertyDto(property);
        return propertyDto;
    }
}
