package com.hypershop.jpa.repository;

import com.hypershop.jpa.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
}
