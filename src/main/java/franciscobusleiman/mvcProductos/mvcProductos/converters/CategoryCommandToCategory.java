package franciscobusleiman.mvcProductos.mvcProductos.converters;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    public Category convert(CategoryCommand categoryCommand) {

        if (categoryCommand == null) {
            return null;
        }
        Category category = new Category();

        category.setId(categoryCommand.getId());
        category.setDescription(category.getDescription());

        return category;
    }
}
