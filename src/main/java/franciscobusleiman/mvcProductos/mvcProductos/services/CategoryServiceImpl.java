package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import franciscobusleiman.mvcProductos.mvcProductos.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Set<Category> getCategories() {
      Set<Category> categories = new HashSet<>();
      categoryRepository.findAll().forEach(categories::add);

      return categories;
    }
}
