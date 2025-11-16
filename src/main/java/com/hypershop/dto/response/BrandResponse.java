package com.hypershop.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BrandResponse {
    private String id;
    private String name;
    private String description;
    private String logoUrl;
    private boolean active;
}
