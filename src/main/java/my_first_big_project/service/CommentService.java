package my_first_big_project.service;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Comment;
import my_first_big_project.entity.Product;
import my_first_big_project.entity.User;
import my_first_big_project.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserService userService;
    private final ProductService productService;
    private final CommentRepository commentRepository;

    public Comment user_comment(Integer estimate, String comment, Long idProduct) {
        User idUser = userService.getCurrentUser();
        Comment comment1 = new Comment();
        comment1.setIdUser(idUser);
        Product product = new Product();
        product.setId(idProduct);
        comment1.setIdProduct(product);
        comment1.setEstimation(estimate);
        comment1.setComment(comment);
        comment1.setBool(true);
        commentRepository.save(comment1);
        return comment1;
    }

    public List<Comment> usersTrueComments(Long idProduct) {
        return commentRepository.findByIdProductAndBool(productService.find_product(idProduct), true);
    }

    public List<Comment> userTrueComment(Long idProduct) {
        return commentRepository.findByIdProductAndIdUser(productService.find_product(idProduct), userService.getCurrentUser());
    }

    public Boolean userHasComment(List<Comment> comments) {
        if (userService.getCurrentUser() == null) {
            return null;
        }
        return !comments.isEmpty();
    }

    public Integer estimate(List<Comment> comments) {
        Integer a = 0;
        for (Comment comment : comments) {
            a += comment.getEstimation();
        }
        if (a != 0) {
            a /= comments.size();
            return a;
        } else {
            return a;
        }
    }

    public List<Comment> allTrueComments() {
        return commentRepository.findByBool(true);
    }

    public List<Comment> allFalseComments() {
        return commentRepository.findByBool(false);
    }

    public Comment changeToTrue(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.ifPresent(value -> value.setBool(true));
        commentRepository.save(comment.orElseThrow());
        return comment.orElseThrow();
    }

    public Comment changeToFalse(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        comment.ifPresent(value -> value.setBool(false));
        commentRepository.save(comment.orElseThrow());
        return comment.orElseThrow();
    }
}