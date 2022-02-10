package com.ntk.repositories;

import com.ntk.pojos.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategory();
    Category getCategory(int categoryId);
}
