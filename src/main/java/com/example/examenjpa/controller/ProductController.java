package com.example.examenjpa.controller;

import com.example.examenjpa.domain.Product;
import com.example.examenjpa.domain.User;
import com.example.examenjpa.exception.UserNotFountException;
import com.example.examenjpa.repository.UserRepository;
import com.example.examenjpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/productos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano", "!ordenar", "!buscar", "!orden"})
    public List<Product> all() {
        return this.productService.all();
    }

    @GetMapping(value = {"","/"}, params = {"!buscar"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                             @RequestParam(value = "tamano", defaultValue = "3") int tamano) {
        Map<String, Object> responseAll = this.productService.all(pagina, tamano);
        return ResponseEntity.ok(responseAll);
    }

//    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano"})
//    public ResponseEntity<Map<String, Object>> allBuscar(@RequestParam(value = "campo", defaultValue = "name") int campo,
//                                                   @RequestParam(value = "valor", defaultValue = "") int valor) {
//
//        Map<String, Object> responseAll = this.productService.all(campo, valor);
//
//        return ResponseEntity.ok(responseAll);
//    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano", "!orden"})
    public ResponseEntity<List<Product>> allbyColumn1(@RequestParam(value = "buscar", required = false, defaultValue = "name,asc") String[] buscar) {
        List<Product> products = this.productService.allbyColumn1(buscar);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamano", "!ordenar", "!buscar"})
    public ResponseEntity<List<Product>> allbyColumn(@RequestParam(value = "orden", required = false, defaultValue = "name,asc") String[] orden) {
        List<Product> products = this.productService.allbyColumn2(orden);
        return ResponseEntity.ok(products);
    }

    @PostMapping({"","/"})
    public Product addProduct(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping("/{id}")
    public Product one(@PathVariable Long id) {
        return this.productService.one(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.productService.delete(id);
    }

    @PutMapping("/{id}")
    public Product replace(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.replace(id, product);
    }

}
