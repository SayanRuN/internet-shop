package my_first_big_project.repositories;

import my_first_big_project.entity.Basket;
import my_first_big_project.entity.Product;
import my_first_big_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByUser(User user);
}