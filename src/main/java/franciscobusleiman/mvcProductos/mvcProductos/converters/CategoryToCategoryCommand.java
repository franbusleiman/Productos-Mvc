package franciscobusleiman.mvcProductos.mvcProductos.converters;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Override
    public CategoryCommand convert(Category category) {
        if (category == null) {
            return null;
        }
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
