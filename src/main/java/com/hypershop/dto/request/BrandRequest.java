package com.hypershop.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BrandRequest {

    private String name;
    private String description;
    private Boolean active;

    // file field
    private MultipartFile logo;
}
