package franciscobusleiman.mvcProductos.mvcProductos.converters;

import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public ProductToProductCommand(CategoryToCategoryCommand categoryToCategoryCommand){
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public ProductCommand convert(Product product) {
        if (product == null) {
            return null;
        }
        ProductCommand productCommand = new ProductCommand();
        productCommand.setId(product.getId());
        productCommand.setDescription(product.getDescription());
        productCommand.setPrice(product.getPrice());
        if(product.getCategory() != null){
            productCommand.setCategory(categoryToCategoryCommand.convert(product.getCategory()));
        }

        return productCommand;
    }
}
