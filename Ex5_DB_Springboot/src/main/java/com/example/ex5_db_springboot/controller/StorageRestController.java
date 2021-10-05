package com.example.ex5_db_springboot.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.ex5_db_springboot.mapper.StorageMapper;
import com.example.ex5_db_springboot.model.PaginationModel;
import com.example.ex5_db_springboot.model.StorageModel;
import com.example.ex5_db_springboot.service.StorageService;
import com.example.ex5_db_springboot.utils.ObjectMapperUtils;
import com.example.ex5_db_springboot.validator.CustomError;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/storages")
@RequiredArgsConstructor
public class StorageRestController {
    private final StorageService storageService;
    private final ObjectMapperUtils modelMapperUtils;
    private final StorageMapper storageMapper;

    @GetMapping("/")
    public ResponseEntity<?> getStorages(@RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "asc") String sortOrder, @RequestParam(defaultValue = "") String search) {
        try {
            // List<StorageModel> storageModels = modelMapperUtils.mapAll(storageService.findAll(0, 0, null, null, null),
            //         StorageModel.class);
            PaginationModel paginationModel = storageService.findAll(page, limit, sortBy, sortOrder, search);
            paginationModel.setData(modelMapperUtils.mapAll(paginationModel.getData(), StorageModel.class));
            return ResponseEntity.ok(paginationModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageModel> getStorage(Long id) {
        try {
            StorageModel storageModel = modelMapperUtils.convertEntityAndDTO(storageService.findById(id),
                    StorageModel.class);
            return ResponseEntity.ok(storageModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StorageModel> deleteStorage(Long id) {
        if (storageService.delete(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStorage(@Valid @RequestBody StorageModel storageModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(bindingResult));
        try {
            storageMapper.update(storageModel);
            return ResponseEntity.ok(storageModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStorage(@Valid @RequestBody StorageModel storageModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(CustomError.getErrorModel(bindingResult));
        try {
            storageMapper.create(storageModel);
            return ResponseEntity.ok(storageModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
