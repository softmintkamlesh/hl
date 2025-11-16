package com.hypershop.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "slug", length = 255)
    private String slug;

    // brand
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    // leaf category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // pricing (MRP inclusive of GST)
    @Column(name = "mrp", nullable = false, precision = 10, scale = 2)
    private BigDecimal mrp;

    @Column(name = "gst_rate", precision = 5, scale = 2)
    private BigDecimal gstRate;   // e.g. 5.00, 12.00

    // variant / pack info
    @Column(name = "pack_size", length = 100)
    private String packSize;      // "500 ml", "1 L"

    @Column(name = "unit_value", precision = 10, scale = 3)
    private BigDecimal unitValue; // 500, 1, etc.

    @Column(name = "unit_uom",
            columnDefinition = "ENUM('ML','L','G','KG','PCS')")
    private String unitUom;       // store raw string, map to enum in code if needed

    @Column(name = "unit_count")
    private Integer unitCount;    // null if not multipack

    @Column(name = "weight_grams")
    private Integer weightGrams;  // normalized for shipping

    // primary image
    @Column(name = "primary_image_url", nullable = false, length = 500)
    private String primaryImageUrl;

    // highlights as JSON
    @Column(name = "highlights_json", columnDefinition = "JSON")
    private String highlightsJson;

    // lifecycle
    @Column(name = "status",
            columnDefinition = "ENUM('ACTIVE','INACTIVE')",
            nullable = false)
    private String status = "ACTIVE";

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted = false;
}
