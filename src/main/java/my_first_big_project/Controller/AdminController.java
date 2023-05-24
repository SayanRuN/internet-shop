package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Comment;
import my_first_big_project.entity.Order;
import my_first_big_project.service.CommentService;
import my_first_big_project.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CommentService commentService;
    private final OrderService orderService;

    @GetMapping(path = "/comments")
    public ModelAndView list_comments() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Admin");
        modelAndView.addObject("trueComments", commentService.allTrueComments());
        modelAndView.addObject("falseComments", commentService.allFalseComments());
        modelAndView.addObject("orders", orderService.allOrders());
        modelAndView.addObject("status", orderService.status());
        return modelAndView;
    }

    @PostMapping(path = "/select")
    public String select_comment(
            @RequestParam Long decision
    ) {
        Comment comment = commentService.changeToTrue(decision);
        return "redirect:/admin/comments";
    }

    @PostMapping(path = "/select2")
    public String select_comment2(
            @RequestParam Long decision2
    ) {
        Comment comment = commentService.changeToFalse(decision2);
        return "redirect:/admin/comments";
    }

    @PostMapping(path = "/status")
    public String changeStatus(
            @RequestParam Long id,
            @RequestParam String position
    ) {
        Order order = orderService.changeStatus(id, position);
        return "redirect:/admin/comments";
    }
}