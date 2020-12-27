package franciscobusleiman.mvcProductos.mvcProductos.commands;

import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;

public class ProductCommand {
    private Long id;
    private String description;
    private int price;
    private CategoryCommand category;

    public ProductCommand(){
    }

    public CategoryCommand getCategory() {
        return category;
    }

    public void setCategory(CategoryCommand category) {
        this.category = category;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
