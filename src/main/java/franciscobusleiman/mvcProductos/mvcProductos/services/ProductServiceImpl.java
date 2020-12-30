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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductToProductCommand productToProductCommand;
    private final ProductCommandToProduct productCommandToProduct;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductCommand productToProductCommand, ProductCommandToProduct productCommandToProduct, CategoryCommandToCategory categoryCommandToCategory, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.productToProductCommand = productToProductCommand;
        this.productCommandToProduct = productCommandToProduct;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryRepository = categoryRepository;
    }

    public Product findById(int id){
       Product product =  productRepository.findById(Long.valueOf(id)).get();
       return product;
    }

public ProductCommand findCommandById(int id){

        Product product = findById(id);
        ProductCommand productCommand = productToProductCommand.convert(product);

        return productCommand;
}
public Set<Product> listAllProducts(){
    Set<Product> products = new HashSet<>();
    productRepository.findAll().forEach(products::add);
    return products;
}

public void delete (Product product){
        productRepository.delete(product);
}

public ProductCommand saveOrUpdate(ProductCommand productCommand){

        Optional<Product> productOptional = listAllProducts().stream()
                                             .filter(product -> product.getId().equals(productCommand.getId())).findFirst();

       Product product;

        if(productOptional.isPresent()){

            product = productOptional.get();

            product.setDescription(productCommand.getDescription());
            product.setPrice(productCommand.getPrice());
            if(productCommand.getCategory() != null) {
                Category category = categoryRepository.findById(productCommand.getCategory().getId()).get();
                product.setCategory(category);
                product.getCategory().getProducts().add(product);            }
        }
        else{
            product = productCommandToProduct.convert(productCommand);
            product.setCategory(categoryRepository.findById(productCommand.getCategory().getId()).get());
            product.getCategory().getProducts().add(product);
        }

        ProductCommand productCommand1 = productToProductCommand.convert(productRepository.save(product));

        return productCommand1;
}
}
