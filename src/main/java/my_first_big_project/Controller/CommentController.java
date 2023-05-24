package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Comment;
import my_first_big_project.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/response")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(path = "/responses")
    public String users_comment(
            @RequestParam(required = false) Integer estimate,
            @RequestParam(required = false) String comment,
            @RequestParam(required = false) Long idProduct
    ) {
        Comment comment1 = commentService.user_comment(estimate, comment, idProduct);
        return "redirect:/create_product/about_product?idProduct=" + idProduct;
    }
}