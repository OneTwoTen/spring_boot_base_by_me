package com.example.ex5_db_springboot.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.ex5_db_springboot.mapper.CategoryMapper;
import com.example.ex5_db_springboot.model.CategoryModel;
import com.example.ex5_db_springboot.model.Metadata;
import com.example.ex5_db_springboot.model.PaginationModel;
import com.example.ex5_db_springboot.service.CategoryService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;
import com.example.ex5_db_springboot.utils.PaginationModelUtil;
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
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {
    private final ObjectMapperUtils modelMapperUtils;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;
    private final Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategory(@PathVariable Long id) {
        try {
            CategoryModel categoryModel = modelMapperUtils.convertEntityAndDTO(categoryService.findById(id),
                    CategoryModel.class);
            logger.info("Category: {}", categoryModel);
            logger.info("Category: {}", categoryService.findById(id));
            return ResponseEntity.ok(categoryModel);
        } catch (Exception e) {
            logger.error("Exception: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategories(@RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "asc") String sortOrder, @RequestParam(defaultValue = "") String search) {
        try {
            // List<CategoryModel> categoryModelList = modelMapperUtils.mapAll(categoryService.findAll(0, 0, "", "", ""),
            //         CategoryModel.class);
            // Metadata metadata = new Metadata(null, 0, 0);
            // metadata.setTotal(Long.valueOf(categoryModelList.size()));
            // metadata.setPage(0);
            // metadata.setLimit(0);
            // return ResponseEntity.ok(PaginationModelUtil.getPaginationModel(metadata, categoryModelList));
            PaginationModel paginationModel = categoryService.findAll(page, limit, sortBy, sortOrder, search);
            System.out.println("paginationModel: " + paginationModel.getData());
            paginationModel.setData(modelMapperUtils.mapAll(paginationModel.getData(), CategoryModel.class));
            return ResponseEntity.ok(paginationModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryModel> deleteCategory(@PathVariable Long id) {
        if (categoryService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryModel categoryModel, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(result));
        try {
            categoryMapper.update(categoryModel);
            return ResponseEntity.ok(categoryModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryModel categoryModel, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(result));
        try {
            categoryMapper.create(categoryModel);
            return ResponseEntity.ok(categoryModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
