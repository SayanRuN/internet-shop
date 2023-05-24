package my_first_big_project.repositories;

import my_first_big_project.entity.Option;
import my_first_big_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}