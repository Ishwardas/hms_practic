package com.jwt8.payload.property;

import lombok.Data;

@Data
public class PropertyDto {
    private String name;
    private Integer no_of_bedrooms;
    private Integer no_of_bathrooms;
    private Integer no_of_guest;
    private Integer no_of_bed;
    private String city;
    private String country;
}
