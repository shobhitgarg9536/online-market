package com.shobhit.dao;

import com.shobhit.dto.Category;

import java.util.List;

public interface CategoryDao {

    Boolean add(Category category);
    List<Category> list();
    Category get(int id);
    Boolean update(Category category);
    Boolean delete(Category category);
}
