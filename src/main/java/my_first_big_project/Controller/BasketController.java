package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Basket;
import my_first_big_project.service.BasketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(path = "/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping(path = "/basket")
    public ModelAndView basketList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Basket");
        modelAndView.addObject("baskets", basketService.basketList());
        return modelAndView;
    }

    @PostMapping(path = "/add_basket")
    public String add_Product(
            @RequestParam(required = false) Long idProduct,
            @RequestParam(required = false) Integer quantity
    ) {
        Basket basket = basketService.add_Product(idProduct, quantity);
        return "redirect:/create_product/list";
    }

    @PostMapping(path = "/delete_basket")
    public String deleteProduct(
            @RequestParam(required = false) Long idBasket
    ) {
        Basket basket = basketService.deleteProduct(idBasket);
        return "redirect:/basket/basket";
    }

    @PostMapping(path = "/increase")
    public String increaseProduct(Long id) {
        Basket basket = basketService.increaseProduct(id);
        return "redirect:/basket/basket";
    }

    @PostMapping(path = "/decrease")
    public String decreaseProduct(Long id) {
        Optional<Basket> basket = basketService.decreaseProduct(id);
        return "redirect:/basket/basket";
    }
}