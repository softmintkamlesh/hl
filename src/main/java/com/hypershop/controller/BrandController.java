package com.hypershop.controller;

import com.hypershop.dto.request.BrandRequest;
import com.hypershop.dto.response.BrandResponse;
import com.hypershop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CATALOG_ADMIN')")
    public BrandResponse createBrand(
            @RequestParam("name") String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestParam("logo") MultipartFile logo
    ) {
        BrandRequest request = new BrandRequest();
        request.setName(name);
        request.setDescription(description);
        request.setActive(active);

        return brandService.createBrand(request, logo);
    }



    // Update brand
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CATALOG_ADMIN')")
    public BrandResponse updateBrand(@PathVariable String id,
                                     @RequestPart("data") BrandRequest request,
                                     @RequestPart(value = "logo", required = false) MultipartFile logo) {
        return brandService.updateBrand(id, request, logo);
    }

    // Get brand (any authenticated user, or even public)
    @GetMapping("/{id}")
    public BrandResponse getBrand(@PathVariable String id) {
        return brandService.getBrand(id);
    }

    // List brands
    @GetMapping
    public List<BrandResponse> listBrands() {
        return brandService.listBrands();
    }
}
