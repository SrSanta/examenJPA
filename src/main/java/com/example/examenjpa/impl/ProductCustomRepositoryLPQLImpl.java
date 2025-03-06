package com.example.examenjpa.impl;

import com.example.examenjpa.domain.Product;
import com.example.examenjpa.repository.ProductCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductCustomRepositoryLPQLImpl implements ProductCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Product> queryCustomProduct(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        StringBuilder queryBuilder = new StringBuilder("SELECT p FROM product p");
        if (buscarOptional.isPresent()) {
            queryBuilder.append(" ").append("WHERE p.name like :name");
        }
        if (ordenarOptional.isPresent()) {
            if ("asc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo ASC");
            } else if ("desc".equalsIgnoreCase(ordenarOptional.get())) {
                queryBuilder.append(" ").append("ORDER BY p.titulo DESC");
            }
        }

        Query query = em.createQuery(queryBuilder.toString());
        if (buscarOptional.isPresent()) {
            query.setParameter("name", "%" + buscarOptional.get() + "%");
        }

        return query.getResultList();
    }
}
