package franciscobusleiman.mvcProductos.mvcProductos.repositories;

import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
