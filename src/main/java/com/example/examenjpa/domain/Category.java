package com.example.examenjpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private long idCategory;

    private String name;

    private String descrip;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> productCategory;

    public long getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescrip() {
        return descrip;
    }

    public List<Product> getProductCategory() {
        return productCategory;
    }
}
