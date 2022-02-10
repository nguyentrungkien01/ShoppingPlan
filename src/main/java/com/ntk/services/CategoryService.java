package com.ntk.services;

import com.ntk.pojos.Category;
import org.json.simple.JSONObject;

import java.util.List;

public interface CategoryService {
    List<JSONObject> getAllCategory();
    Category getCategory(int categoryId);
}
