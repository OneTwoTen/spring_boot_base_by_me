package com.example.ex5_db_springboot.dao;

import java.util.List;

import com.example.ex5_db_springboot.model.ProductModel;
import org.springframework.stereotype.Service;

@Service
public interface ProductDao{

    void insertProduct(ProductModel productModel);

    ProductModel findById(Long id);

    void updateProduct(ProductModel productModel);

    void deleteProduct(Long id);

    void deleteAllProducts();

    int getProductCount();

    List<ProductModel> getAllProducts();
}
