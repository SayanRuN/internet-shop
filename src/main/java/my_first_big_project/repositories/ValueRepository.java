package my_first_big_project.repositories;

import my_first_big_project.entity.Product;
import my_first_big_project.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ValueRepository extends JpaRepository<Value,Long> {
    List<Value> findByIdProduct(Product product);
}