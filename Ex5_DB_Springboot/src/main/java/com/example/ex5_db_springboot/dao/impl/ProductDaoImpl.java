package com.example.ex5_db_springboot.dao.impl;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.*;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import com.example.ex5_db_springboot.dao.ProductDao;
import com.example.ex5_db_springboot.entity.CategoryEntity;
import com.example.ex5_db_springboot.entity.ProductEntity;
import com.example.ex5_db_springboot.entity.StorageEntity;
import com.example.ex5_db_springboot.model.ProductModel;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl implements ProductDao {
    private final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    private final DataSource dataSource;
    private final ObjectMapperUtils objectMapperUtils;
    private final Validator validator;
    private SimpleJdbcCall simpleJdbcCall;

    public ProductDaoImpl(DataSource dataSource, ObjectMapperUtils objectMapperUtils, Validator validator) {
        this.dataSource = dataSource;
        this.objectMapperUtils = objectMapperUtils;
        this.validator = validator;
    }

    @Override
    public void insertProduct(@Valid ProductModel productModel) {
        try {
            validate(productModel);
            this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("insert_product");
            ProductEntity product = objectMapperUtils.convertEntityAndDTO(productModel, ProductEntity.class);
            SqlParameterSource in = new MapSqlParameterSource().addValue("code", product.getCode())
                    .addValue("category_id", productModel.getCategory())
                    .addValue("storage_id", productModel.getStorage()).addValue("name", product.getName())
                    .addValue("description", product.getDescription()).addValue("image", product.getImage())
                    .addValue("quantity", product.getQuantity()).addValue("quantity_sold", product.getQuantitySold())
                    .addValue("created_date", new Timestamp(System.currentTimeMillis()))
                    .addValue("updated_date", new Timestamp(System.currentTimeMillis()))
                    .addValue("price", product.getPrice());
            simpleJdbcCall.execute(in);
            logger.info("Insert success!");
        } catch (Exception e) {
            logger.error("Can't not insert product: {}", e.toString());
        }
    }

    @Override
    public ProductModel findById(Long id) {
        if (id == null) {
            return null;
        }
        try {
            simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("get_product");
            SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
            Map<String, Object> out = simpleJdbcCall.execute(in);
            if (out.isEmpty()) {
                return null;
            }
            ProductEntity product = new ProductEntity();
            product.setId((Long) out.get("id"));
            product.setCode((String) out.get("code"));
            product.setCategory((CategoryEntity) out.get("category_id"));
            product.setName((String) out.get("name"));
            product.setPrice((BigDecimal) out.get("price"));
            product.setQuantity((Integer) out.get("quantity"));
            product.setStorage((StorageEntity) out.get("storage_id"));
            product.setQuantitySold((Integer) out.get("quantity_sold"));
            logger.info("product: {}", product);
            return objectMapperUtils.convertEntityAndDTO(product, ProductModel.class);
        } catch (Exception e) {
            logger.error("Can't not find product: {}", e.toString());
            return null;
        }
    }

    @Override
    public void updateProduct(ProductModel productModel) {
       try {
        validate(productModel);
        simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("update_product");
        ProductEntity product = objectMapperUtils.convertEntityAndDTO(productModel, ProductEntity.class);
        SqlParameterSource in = new MapSqlParameterSource().addValue("product_id", product.getId())
                .addValue("product_name", product.getName()).addValue("product_price", product.getPrice())
                .addValue("product_quantity", product.getQuantity());
        simpleJdbcCall.execute(in);
        logger.info("updated_date success!");
       } catch (Exception e) {
           logger.error("Can't not update product: {}", e.toString());
       }
    }

    private void validate(ProductModel productModel) {
        Set<ConstraintViolation<ProductModel>> violations = validator.validate(productModel);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<ProductModel> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("delete_product");
            SqlParameterSource in = new MapSqlParameterSource().addValue("id", id);
            simpleJdbcCall.execute(in);
        } catch (Exception e) {
            logger.error("error: {}", e.toString());
        }
    }

    @Override
    public void deleteAllProducts() {
        try {
            simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("delete_all_products");
            simpleJdbcCall.execute();
        } catch (Exception e) {
            logger.error("error: {}", e.toString());
        }
    }

    @Override
    public int getProductCount() {
        SimpleJdbcCall call = new SimpleJdbcCall(dataSource).withProcedureName("get_product_count");
        Map<String, Object> out = call.execute();
        return ((BigDecimal) out.get("product_count")).intValue();
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductModel> productModels = null;
        try {
            this.simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("get_all_products")
                    .returningResultSet("products", BeanPropertyRowMapper.newInstance(ProductEntity.class));
            SqlParameterSource in = new MapSqlParameterSource();
            Map<String, Object> out = simpleJdbcCall.execute(in);
            logger.info("out: {}", out);
            List<ProductEntity> products = (List<ProductEntity>) out.get("products");
            for (ProductEntity p : products) {
                logger.info("p: {}", p);
            }
            productModels = objectMapperUtils.mapAll(products, ProductModel.class);
        } catch (Exception e) {
            logger.error("Exception: {}", e.toString());
        }
        return productModels;
    }

}
