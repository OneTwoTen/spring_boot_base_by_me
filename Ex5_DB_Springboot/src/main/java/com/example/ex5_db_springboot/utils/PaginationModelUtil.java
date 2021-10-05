package com.example.ex5_db_springboot.utils;

import java.util.List;

import com.example.ex5_db_springboot.model.Metadata;
import com.example.ex5_db_springboot.model.PaginationModel;

public class PaginationModelUtil {
    public static PaginationModel getPaginationModel(Long total, int page, int limit, List<?> list) {
        PaginationModel paginationModel = new PaginationModel();
        Metadata metadata = new Metadata(total, page, limit);
        paginationModel.setMetadata(metadata);
        paginationModel.setData(list);
        return paginationModel;
    }
}
