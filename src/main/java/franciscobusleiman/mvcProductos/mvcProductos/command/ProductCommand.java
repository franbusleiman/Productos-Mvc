package franciscobusleiman.mvcProductos.mvcProductos.command;

import franciscobusleiman.mvcProductos.mvcProductos.domain.Category;

public class ProductCommand {
    private Long id;
    private String description;
    private int price;
    private Category category;

    public ProductCommand(){
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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
