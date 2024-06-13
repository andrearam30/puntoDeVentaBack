package management.iconic.iconicmx.subcategory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import management.iconic.iconicmx.category.model.Category;
import management.iconic.iconicmx.status.model.Status;

@Entity
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Subcategory() {
    }

    public Subcategory(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Subcategory(String name, String description, Category category, Status status) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
