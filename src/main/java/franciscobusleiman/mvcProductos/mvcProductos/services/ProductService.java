package franciscobusleiman.mvcProductos.mvcProductos.services;

import franciscobusleiman.mvcProductos.mvcProductos.commands.ProductCommand;
import franciscobusleiman.mvcProductos.mvcProductos.domain.Product;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public interface ProductService {


void delete(Product product);
Product findById(int id);
ProductCommand findCommandById(int id);
Set<Product> listAllProducts();
ProductCommand saveOrUpdate(ProductCommand productCommand);

}

