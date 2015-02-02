package api.sandbox.catalog.model;

import java.util.List;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public interface ProductCatalog {
    void setCategory(Category category);

    Category getCategory();

    List<Product> getProductList();

    void setProductList(List<Product> productList);

}
