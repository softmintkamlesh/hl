package com.hypershop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BrandRequest {

    private String name;
    private String description;
    private Boolean active;

    // file field
    private MultipartFile logo;
}
