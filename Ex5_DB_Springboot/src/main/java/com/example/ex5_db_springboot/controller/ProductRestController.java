package com.example.ex5_db_springboot.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.ex5_db_springboot.mapper.ProductMapper;
import com.example.ex5_db_springboot.model.PaginationModel;
import com.example.ex5_db_springboot.model.ProductModel;
import com.example.ex5_db_springboot.service.ProductService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;
import com.example.ex5_db_springboot.validator.CustomError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final ObjectMapperUtils modelMapperUtils;
    private final ProductMapper productMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public ResponseEntity<?> getProducts(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "") String search) {
        try {
            // List<ProductModel> productModels =
            // modelMapperUtils.mapAll(productService.findAll(0, 0, null, null, null),
            // ProductModel.class);
            PaginationModel paginationModel = productService.findAll(page, limit, sortBy, sortOrder, search);
            paginationModel.setData(modelMapperUtils.mapAll(paginationModel.getData(), ProductModel.class));
            return ResponseEntity.ok(paginationModel);
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable("id") Long id) {
        try {
            ProductModel productModel = modelMapperUtils.convertEntityAndDTO(productService.findById(id),
                    ProductModel.class);
            return ResponseEntity.ok(productModel);
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable("id") Long id) {
        if (productService.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductModel productModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(bindingResult));
        try {
            productMapper.update(productModel);
            return ResponseEntity.ok(productModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductModel productModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(bindingResult));
        try {
            productMapper.create(productModel);
            return ResponseEntity.ok(productModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
