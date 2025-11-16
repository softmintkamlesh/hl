package com.hypershop.service.impl;

import com.hypershop.dto.request.BrandRequest;
import com.hypershop.dto.response.BrandResponse;
import com.hypershop.jpa.model.Brand;
import com.hypershop.jpa.repository.BrandRepository;
import com.hypershop.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final S3StorageService s3StorageService;

    public BrandServiceImpl(BrandRepository brandRepository,
                            S3StorageService s3StorageService) {
        this.brandRepository = brandRepository;
        this.s3StorageService = s3StorageService;
    }

    @Override
    public BrandResponse createBrand(BrandRequest request, MultipartFile logo) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setActive(request.getActive() == null || request.getActive());

        if (logo != null && !logo.isEmpty()) {
            String url = s3StorageService.uploadBrandLogo(logo);
            brand.setLogoUrl(url);
        }

        Brand saved = brandRepository.save(brand);
        return toResponse(saved);
    }

    @Override
    public BrandResponse updateBrand(String id, BrandRequest request, MultipartFile logo) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        if (request.getName() != null) {
            brand.setName(request.getName());
        }
        brand.setDescription(request.getDescription());

        if (request.getActive() != null) {
            brand.setActive(request.getActive());
        }

        if (logo != null && !logo.isEmpty()) {
            String url = s3StorageService.uploadBrandLogo(logo);
            brand.setLogoUrl(url);
        }

        Brand saved = brandRepository.save(brand);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getBrand(String id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));
        return toResponse(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponse> listBrands() {
        return brandRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    private BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .active(brand.isActive())
                .build();
    }
}
