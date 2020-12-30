package franciscobusleiman.mvcProductos.mvcProductos.controllers;

import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import franciscobusleiman.mvcProductos.mvcProductos.services.CategoryService;
import franciscobusleiman.mvcProductos.mvcProductos.services.ProductService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"", "index", "/index"})
    public String getProducts(Model model){

        Set<Product> products = productService.listAllProducts();

        model.addAttribute("products", products);

        return "index";
    }
    @GetMapping("/product/{id}/show")
    public String getProduct(@PathVariable int id, Model model){

        ProductCommand productCommand = productService.findCommandById(id);
        model.addAttribute("product", productCommand);

        return "product/show";
    }

    @GetMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable int id, Model model){
        Product product = productService.findById(id);

        productService.delete(product);

        return "redirect:/index";
    }
    @GetMapping("/product/newRecipe/form")
    public String newProduct(Model model){

        ProductCommand productCommand = new ProductCommand();

        model.addAttribute("product", productCommand);
        model.addAttribute("categories", categoryService.getCategories());

        return "product/form";
    }
    @GetMapping("/product/{id}/form")
    public String updateProduct(@PathVariable int id, Model model){


        ProductCommand productCommand = productService.findCommandById(id);

        model.addAttribute("product", productCommand);
        model.addAttribute("categories", categoryService.getCategories());


        return "product/form";
    }

    @PostMapping("/product/saveRecipe")
    public String SaveRecipe(@ModelAttribute ProductCommand productCommand){

        productService.saveOrUpdate(productCommand);

        return "redirect:/index";
    }
}
