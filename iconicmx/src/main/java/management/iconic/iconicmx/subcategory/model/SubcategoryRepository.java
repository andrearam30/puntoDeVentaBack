package management.iconic.iconicmx.subcategory.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);
    List<Subcategory> findAllByCategoryId(long id);
    boolean existsById(long id);
}
