package com.jwt8.repository;

import com.jwt8.entity.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}