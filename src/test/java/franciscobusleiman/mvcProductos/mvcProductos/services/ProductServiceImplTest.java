package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.converters.CategoryCommandToCategory;
import franciscobusleiman.mvcProductos.mvcProductos.converters.ProductCommandToProduct;
import franciscobusleiman.mvcProductos.mvcProductos.converters.ProductToProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import franciscobusleiman.mvcProductos.mvcProductos.repositories.CategoryRepository;
import franciscobusleiman.mvcProductos.mvcProductos.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProductCommandToProduct productCommandToProduct;
    @Mock
    ProductToProductCommand productToProductCommand;
    @Mock
    CategoryCommandToCategory categoryCommandToCategory;

    ProductService productServiceimpl;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

         productServiceimpl = new ProductServiceImpl(productRepository, productToProductCommand, productCommandToProduct, categoryCommandToCategory, categoryRepository);
    }

    @Test
    void findById() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("tenedor");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

         Product product1 = productServiceimpl.findById(1);

        verify(productRepository, times(1)).findById(anyLong());
        assertEquals("tenedor", product1.getDescription());

    }

    @Test
    void findCommandById() {
        Product product = new Product();
        product.setId(1L);
        product.setDescription("tenedor");

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(1L);
       productCommand.setDescription("cuchillo");
        when(productToProductCommand.convert(any())).thenReturn(productCommand);

        ProductCommand productCommand1 = productServiceimpl.findCommandById(1);

        verify(productToProductCommand, times(1)).convert(any());
        assertEquals("cuchillo", productCommand1.getDescription());
    }

    @Test
    void listAllProducts() {

        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Product product3 = new Product();
        product3.setId(3L);

        Set<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        when(productRepository.findAll()).thenReturn(products);

        Set<Product> products1 = productServiceimpl.listAllProducts();
        verify(productRepository, times(1)).findAll();
        assertEquals(3, products1.size());
    }

    @Test
    void delete() {
        Product product = new Product();
        product.setId(1L);

        productServiceimpl.delete(product);

        verify(productRepository, times(1)).delete(any());
    }

    @Test
    void saveOrUpdate() {
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        Product product3 = new Product();
        product3.setId(3L);

        Set<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        when(productRepository.findAll()).thenReturn(products);

        Product product = new Product();
        when(productCommandToProduct.convert(any())).thenReturn(product);


        Category category = new Category();
        category.setId(1L);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(anyLong());

        ProductCommand productCommand = new ProductCommand();
        productCommand.setCategory(categoryCommand);


        Product product4 = new Product();
        product4.setId(10L);
        when(productRepository.save(product)).thenReturn(product4);

        ProductCommand productCommand1 = new ProductCommand();
        productCommand1.setId(5L);

        when(productToProductCommand.convert(product4)).thenReturn(productCommand1);


        ProductCommand productCommand2 = new ProductCommand();
        productCommand2.setCategory(categoryCommand);

        productServiceimpl.saveOrUpdate(productCommand2);


        verify(categoryRepository, times(1)).findById(anyLong());
        verify(productCommandToProduct, times(1)).convert(any());
        verify(productToProductCommand, times(1)).convert(any());
        verify(productRepository, times(1)).save(any());
    }
}