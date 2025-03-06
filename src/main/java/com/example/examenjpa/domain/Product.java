package com.example.examenjpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private long id;

    public long getId() {
        return id;
    }

    private String name;

    private String descip;

    private String image_url;

    private BigDecimal price;

    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    public String getName() {
        return name;
    }

    public String getDescip() {
        return descip;
    }

    public String getImage_url() {
        return image_url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }
}
