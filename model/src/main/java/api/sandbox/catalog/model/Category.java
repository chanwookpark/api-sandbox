package api.sandbox.catalog.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public class Category {

    private long categoryId;
    private List<Category> subCategories;

    public Category() {
    }

    public Category(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void loadSubCategory(int offset, int limit) {
        this.subCategories = Collections.nCopies(limit, new Category());
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
