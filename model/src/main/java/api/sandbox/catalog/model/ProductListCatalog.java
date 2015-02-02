package api.sandbox.catalog.model;

import java.util.List;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public class ProductListCatalog implements ProductCatalog {

    private Category category;
    private List<Product> productList;

    @Override
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }
}
