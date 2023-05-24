package my_first_big_project.repositories;

import my_first_big_project.entity.Comment;
import my_first_big_project.entity.Product;
import my_first_big_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdProductAndIdUser(Product product, User user);

    List<Comment> findByIdProductAndBool(Product product, Boolean bool);

    List<Comment> findByBool(Boolean bool);
}