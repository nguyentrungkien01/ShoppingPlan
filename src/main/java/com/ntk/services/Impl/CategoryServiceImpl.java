package com.ntk.services.Impl;

import com.ntk.controllers.UtilsController;
import com.ntk.pojos.Category;
import com.ntk.repositories.CategoryRepository;
import com.ntk.services.CategoryService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<JSONObject> getAllCategory() {
        List<Category> categories = categoryRepository.getAllCategory();
        if (categories == null)
            return null;
        List<JSONObject> jsonObjects = new ArrayList<>();
        categories.forEach(e -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categoryId",
                    UtilsController.encodeBase64(String.valueOf(e.getCategoryId())));
            jsonObject.put("categoryName", e.getName());
            jsonObjects.add(jsonObject);
        });
        return jsonObjects;
    }

    @Override
    @Transactional
    public Category getCategory(int categoryId) {
        return categoryRepository.getCategory(categoryId);
    }
}
