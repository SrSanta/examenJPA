package com.example.examenjpa.service;

import com.example.examenjpa.domain.Product;
import com.example.examenjpa.exception.ProductNotFoundException;
import com.example.examenjpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> all(){return this.productRepository.findAll();}

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product one(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }
    public Product replace(Long id, Product product) {
        return this.productRepository.findById(id).map( p -> (id.equals(product.getId())  ?
                this.productRepository.save(product) : null)).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Map<String, Object> all(int pagina, int tamano) {
        Pageable paginado = PageRequest.of(pagina, tamano, Sort.by("id").ascending());

        Page<Product> pageAll = this.productRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public Map<String, Object> allBuscar(int campo, int valor) {
        Pageable paginado = PageRequest.of(campo, valor, Sort.by("id").ascending());

        Page<Product> pageAll = this.productRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

    public List<Product> allbyColumn2(String[] orden) {
        Sort sort = null;
        if (orden != null && orden.length == 2) {
            String columna = orden[0];
            String sentido = orden[1];
            if("asc".equalsIgnoreCase(sentido)){
                sort = Sort.by(columna).ascending();
            } else {
                sort = Sort.by(columna).descending();
            }
        }
        return productRepository.findAll(sort);
    }

    public List<Product> allbyColumn1(String[] buscar) {
        Sort sort = null;
        if (buscar != null && buscar.length == 2) {
            String columna = buscar[0];
            String sentido = buscar[1];
            if("asc".equalsIgnoreCase(sentido)){
                sort = Sort.by(columna).ascending();
            } else {
                sort = Sort.by(columna).descending();
            }
        }
        return productRepository.findAll(sort);
    }
}
