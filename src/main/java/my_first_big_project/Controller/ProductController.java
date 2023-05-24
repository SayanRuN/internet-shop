package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.*;
import my_first_big_project.service.CommentService;
import my_first_big_project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/create_product")
public class ProductController {

    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping(path = "/list")
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Products");
        modelAndView.addObject("categories", productService.categoryList());
        modelAndView.addObject("products", productService.productList());
        return modelAndView;
    }

    @PostMapping(path = "/list")
    public String addProduct(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price
    ) {
        Product product = productService.add_product(category, name, price);
        return "redirect:/create_product/list";
    }

    @GetMapping(path = "/about_product")
    public ModelAndView about_product(
            @RequestParam(required = false) Long idProduct
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.find_product(idProduct));
        modelAndView.addObject("values", productService.find_value(idProduct));
        modelAndView.addObject("comments", commentService.usersTrueComments(idProduct));
        modelAndView.addObject("commentList", commentService.userTrueComment(idProduct));
        modelAndView.addObject("usersComment", commentService.userHasComment(commentService.userTrueComment(idProduct)));
        modelAndView.addObject("estimate", commentService.estimate(commentService.usersTrueComments(idProduct)));
        modelAndView.setViewName("About_product");
        return modelAndView;
    }
}