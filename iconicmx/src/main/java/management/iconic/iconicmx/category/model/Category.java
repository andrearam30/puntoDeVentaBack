package management.iconic.iconicmx.category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import management.iconic.iconicmx.status.model.Status;
import management.iconic.iconicmx.subcategory.model.Subcategory;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "status_id")
    @NotNull
    private Status status;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Subcategory> subcategories;

    public Category() {
    }

    public Category(String name, String description, Status status, List<Subcategory> subcategories) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.subcategories = subcategories;
    }

    public Category(String name, String description, Status status) {
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

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }
}
