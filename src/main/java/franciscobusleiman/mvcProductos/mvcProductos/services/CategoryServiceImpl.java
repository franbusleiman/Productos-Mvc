package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.converters.CategoryToCategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import franciscobusleiman.mvcProductos.mvcProductos.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryToCategoryCommand categoryToCategoryCommand;

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand){
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }
    @Override
    public Set<CategoryCommand> getCategories() {
      Set<Category> categories1 = new HashSet<>();
      categoryRepository.findAll().forEach(categories1::add);

      Set<CategoryCommand> categories = categories1.stream()
                           .map(category -> categoryToCategoryCommand.convert(category)).collect(Collectors.toSet());

      return categories;
    }
}
