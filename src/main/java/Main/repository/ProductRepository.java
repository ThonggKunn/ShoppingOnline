package Main.repository;

import Main.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE (:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:category IS NULL OR p.category LIKE CONCAT('%', :category, '%'))")
    List<Product> findByNameAndCategory(@Param("name") String name,
                                        @Param("category") String category);

}
