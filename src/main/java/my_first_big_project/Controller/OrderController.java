package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.*;
import my_first_big_project.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping(path = "/orderList")
    public ModelAndView order() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Order");
        return modelAndView;
    }

    @PostMapping(path = "/add_order")
    public String addOrder(
            @RequestParam(required = false) String street,
            @RequestParam(required = false) Integer house,
            @RequestParam(required = false) Integer flat
    ) {
        Order order = orderService.order(street, house, flat);
        return "redirect:/order/orderList";
    }
}