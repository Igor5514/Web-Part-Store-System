package com.example.server_354.object;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "parts")
public class Parts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    @Column(nullable = false)
    private String name;

    @Column(name = "part_number", nullable = false)
    private String partNumber;

    @Column(name = "brand")
    private String brand;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity = 0;

    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(precision = 6, scale = 2)
    private BigDecimal weight;

    private String dimensions;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "date_added", updatable = false)
    private LocalDateTime dateAdded;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;


    @PrePersist
    protected void onCreate() {
        this.dateAdded = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }


    public Parts() {}

}
