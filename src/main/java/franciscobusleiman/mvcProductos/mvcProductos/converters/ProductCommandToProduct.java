package franciscobusleiman.mvcProductos.mvcProductos.converters;

import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

private final CategoryCommandToCategory categoryCommandToCategory;

public ProductCommandToProduct(CategoryCommandToCategory categoryCommandToCategory){
    this.categoryCommandToCategory = categoryCommandToCategory;
}
    @Override
    public Product convert(ProductCommand productCommand) {
        if (productCommand == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productCommand.getId());
        product.setDescription(productCommand.getDescription());
        product.setPrice(productCommand.getPrice());
        if (productCommand.getCategory() != null) {
            product.setCategory(categoryCommandToCategory.convert(productCommand.getCategory()));
        }

        return product;
    }
}
