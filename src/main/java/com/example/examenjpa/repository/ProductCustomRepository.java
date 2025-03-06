package com.example.examenjpa.repository;

import com.example.examenjpa.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCustomRepository {
    public List<Product> queryCustomProduct(Optional<String> buscarOptional, Optional<String> ordenarOptional);
}
