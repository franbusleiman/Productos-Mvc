package franciscobusleiman.mvcProductos.mvcProductos.controllers;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import franciscobusleiman.mvcProductos.mvcProductos.services.CategoryService;
import franciscobusleiman.mvcProductos.mvcProductos.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.ParserOutput;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    ProductService productService;
    @Mock
    CategoryService categoryService;

    ProductController productController;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

         ProductController productController = new ProductController(productService, categoryService);

         this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getProducts() throws Exception {

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

        when(productService.listAllProducts()).thenReturn(products);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).listAllProducts();
        assertEquals(3 , productService.listAllProducts().size());
    }

    @Test
    void getProduct() throws Exception {

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(1L);

        when(productService.findCommandById(anyInt())).thenReturn(productCommand);

        mockMvc.perform(get("/product/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attributeExists("product"));


        verify(productService, times(1)).findCommandById(anyInt());

    }

    @Test
    void deleteProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        when(productService.findById(anyInt())).thenReturn(product);

        mockMvc.perform(get("/product/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));

        verify(productService, times(1)).findById(anyInt());
        verify(productService, times(1)).delete(any());
    }

    @Test
    void newProduct() throws Exception {

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(1L);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(2L);

        CategoryCommand category3 = new CategoryCommand();
        category3.setId(3L);

        Set<CategoryCommand> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        when(categoryService.getCategories()).thenReturn(categories);

        mockMvc.perform(get("/product/newRecipe/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/form"))
                .andExpect(model().attributeExists("product", "categories"));


        verify(categoryService, times(1)).getCategories();
        assertEquals(3, categoryService.getCategories().size());
    }

    @Test
    void updateProduct() throws Exception {

        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(1L);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(1L);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(2L);

        CategoryCommand category3 = new CategoryCommand();
        category3.setId(3L);

        Set<CategoryCommand> categories = new HashSet<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);

        when(categoryService.getCategories()).thenReturn(categories);
        when(productService.findCommandById(anyInt())).thenReturn(productCommand);

        mockMvc.perform(get("/product/1/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/form"))
                .andExpect(model().attributeExists("product", "categories"));

        verify(categoryService, times(1)).getCategories();
        verify(productService, times(1)).findCommandById(anyInt());
        assertEquals(3, categoryService.getCategories().size());
    }

    @Test
    void saveRecipe() throws Exception {
        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(1L);

        when(productService.saveOrUpdate(any())).thenReturn(productCommand);

        mockMvc.perform(post("/product/saveRecipe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));

        verify(productService, times(1)).saveOrUpdate(any());
    }
}