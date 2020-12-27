package franciscobusleiman.mvcProductos.mvcProductos.commands;

public class CategoryCommand {
    private Long id;
    private String description;

    public CategoryCommand(){
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
}
