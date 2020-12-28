package franciscobusleiman.mvcProductos.mvcProductos.controllers;

import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import franciscobusleiman.mvcProductos.mvcProductos.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
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
}
