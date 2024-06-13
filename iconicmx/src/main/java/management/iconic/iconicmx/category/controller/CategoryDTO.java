package management.iconic.iconicmx.category.controller;

import jakarta.validation.constraints.NotNull;
import management.iconic.iconicmx.status.model.Status;

public class CategoryDTO {
    long id;
    @NotNull
    String name;
    @NotNull
    String description;
    @NotNull
    Status status;

    public CategoryDTO() {
    }

    public CategoryDTO(long id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
