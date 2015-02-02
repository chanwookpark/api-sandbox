package api.sandbox.catalog;

import api.sandbox.catalog.model.Category;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public interface CategoryService {
    Category findCategory(long categoryId);
}
