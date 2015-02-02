package api.sandbox.catalog;

import api.sandbox.catalog.model.ProductCatalog;

/**
 * Created by chanwook on 2015. 2. 2..
 */
public interface CatalogService {
    ProductCatalog getProductListCatalog(long categoryId, int offset, int limit);
}
