package franciscobusleiman.mvcProductos.mvcProductos.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void getId() {
        Long id = 5L;
        product.setId(id);

        assertEquals(id, product.getId());
    }

    @Test
    void getCategory() {
        Category category = new Category();
        category.setDescription("cocina");
        product.setCategory(category);

        assertEquals("cocina", product.getCategory().getDescription());
    }

    @Test
    void getDescription() {
        product.setDescription("lava vajilla");

        assertEquals("lava vajilla", product.getDescription());
    }


    @Test
    void getPrice() {
        int price = 3000;
        product.setPrice(price);

        assertEquals(price, product.getPrice());
    }

}