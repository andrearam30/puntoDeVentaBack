package management.iconic.iconicmx.subcategory.controller;

import jakarta.validation.constraints.NotNull;
import management.iconic.iconicmx.category.model.Category;

public class SubcategoryDTO {
    long id;
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    Category category;

    public SubcategoryDTO() {
    }

    public SubcategoryDTO(long id, String name, String description, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
