package my_first_big_project.repositories;

import my_first_big_project.entity.ValueOfOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueOfOrderRepository extends JpaRepository<ValueOfOrder,Long> {
}