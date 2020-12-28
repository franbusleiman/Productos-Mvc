package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.converters.CategoryCommandToCategory;
import franciscobusleiman.mvcProductos.mvcProductos.converters.ProductCommandToProduct;
import franciscobusleiman.mvcProductos.mvcProductos.converters.ProductToProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
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

    public ProductServiceImpl(ProductRepository productRepository, ProductToProductCommand productToProductCommand, ProductCommandToProduct productCommandToProduct, CategoryCommandToCategory categoryCommandToCategory){
        this.productRepository = productRepository;
        this.productToProductCommand = productToProductCommand;
        this.productCommandToProduct = productCommandToProduct;
        this.categoryCommandToCategory = categoryCommandToCategory;
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

    Product product = productOptional.get();

        if(productOptional.isPresent()){

            product.setDescription(productCommand.getDescription());
            product.setPrice(productCommand.getPrice());
            if(productCommand.getCategory() != null) {
                product.setCategory(categoryCommandToCategory.convert(productCommand.getCategory()));
            }
        }
        else{
            product = productCommandToProduct.convert(productCommand);
        }

        ProductCommand productCommand1 = productToProductCommand.convert(productRepository.save(product));

        return productCommand1;
}
}
