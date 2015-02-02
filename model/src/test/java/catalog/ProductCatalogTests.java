package catalog;

import api.sandbox.catalog.CatalogService;
import api.sandbox.catalog.CatalogServiceImpl;
import api.sandbox.catalog.CategoryService;
import api.sandbox.catalog.CategoryServiceImpl;
import api.sandbox.catalog.model.Category;
import api.sandbox.catalog.model.ProductCatalog;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Category-Product 간의 관계를 통해 모델 설계 연습
 * <p/>
 * Created by chanwook on 2015. 2. 2..
 */
public class ProductCatalogTests {
    private CategoryService categoryService = new CategoryServiceImpl();
    private CatalogService catalogService = new CatalogServiceImpl();

    /**
     * 보통의 쇼핑몰 페이지의 메인에 나오는 1뎁스의 카테고리, 한 카테고리의 서브와 상품 목록을 조회하는 코드
     * 화면이 작은 모바일을 생각하는 게 더 적절해 보임
     *
     * @throws Exception
     */
    @Test
    public void findCategoryAndProduct() throws Exception {
        long categoryId = 1;
        int productLimit = 20;
        int categoryLimit = 10;
        int offset = 0;

        Category root = categoryService.findCategory(categoryId);
        assertNotNull(root);
        assertEquals(categoryId, root.getCategoryId());

        // 어떻게? 1. service 짠다 2. JPA의 lazy loading과 같은 persistence 기술을 사용한다 3. model에 loading 로직을 짠다
        root.loadSubCategory(offset, categoryLimit);
        assertTrue(root.getSubCategories().size() == categoryLimit);

        ProductCatalog catalog = catalogService.getProductListCatalog(categoryId, offset, productLimit);
        catalog.setCategory(root);

        assertTrue(catalog.getProductList().size() == productLimit);
    }
}
