package franciscobusleiman.mvcProductos.mvcProductos.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;

    @OneToOne(mappedBy = "category")
    private Product product;

    public Category(){}

    public Category(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }
}
