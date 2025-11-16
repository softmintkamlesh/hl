package com.hypershop.service;

import com.hypershop.dto.request.BrandRequest;
import com.hypershop.dto.response.BrandResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BrandService {

    BrandResponse createBrand(BrandRequest request, MultipartFile logo);

    BrandResponse updateBrand(String id, BrandRequest request, MultipartFile logo);

    BrandResponse getBrand(String id);

    List<BrandResponse> listBrands();
}
