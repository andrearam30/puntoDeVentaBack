package management.iconic.iconicmx.status.controller;

import jakarta.validation.constraints.NotNull;

public class StatusDTO {
    long id;
    @NotNull
    String description;

    public StatusDTO() {
    }

    public StatusDTO(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
