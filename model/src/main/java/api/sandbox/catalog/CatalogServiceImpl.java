package api.sandbox.catalog;

import api.sandbox.catalog.model.Product;
import api.sandbox.catalog.model.ProductCatalog;
import api.sandbox.catalog.model.ProductListCatalog;

import java.util.Collections;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public class CatalogServiceImpl implements CatalogService {
    @Override
    public ProductCatalog getProductListCatalog(long categoryId, int offset, int limit) {
        ProductCatalog catalog = new ProductListCatalog();
        catalog.setProductList(Collections.nCopies(limit, new Product()));
        return catalog;
    }
}
