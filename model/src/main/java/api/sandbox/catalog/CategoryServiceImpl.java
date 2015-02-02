package api.sandbox.catalog;

import api.sandbox.catalog.model.Category;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category findCategory(long categoryId) {
        Category category = new Category(categoryId);
        return category;
    }
}
