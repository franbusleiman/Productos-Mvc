package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.commands.CategoryCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface CategoryService {

    Set<CategoryCommand> getCategories();
}
