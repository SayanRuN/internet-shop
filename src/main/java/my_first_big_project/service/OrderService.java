package my_first_big_project.service;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Basket;
import my_first_big_project.entity.Order;
import my_first_big_project.entity.User;
import my_first_big_project.entity.ValueOfOrder;
import my_first_big_project.enumuration.Status;
import my_first_big_project.repositories.BasketRepository;
import my_first_big_project.repositories.OrderRepository;
import my_first_big_project.repositories.ValueOfOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ValueOfOrderRepository valueOfOrderRepository;
    private final BasketRepository basketRepository;

    public Order order(String street, Integer house, Integer flat) {
        User idUser = userService.getCurrentUser();
        List<Basket> baskets = basketRepository.findByUser(idUser);
        Order order = new Order();
        order.setIdUser(idUser);
        order.setStatus(Status.EMPTY);
        order.setStreet(street);
        order.setHouse(house);
        order.setFlat(flat);
        order.setDateOrder(LocalDateTime.now());
        orderRepository.save(order);
        for (Basket basket : baskets) {
            ValueOfOrder valueOfOrder = new ValueOfOrder();
            valueOfOrder.setIdOrder(order);
            valueOfOrder.setIdProduct(basket.getProduct());
            valueOfOrder.setAmount(basket.getQuantity());
            valueOfOrderRepository.save(valueOfOrder);
            basketRepository.delete(basket);
        }
        return order;
    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    public Status[] status() {
        return Status.values();
    }

    public Order changeStatus(Long id, String position) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(value -> value.setStatus(Status.valueOf(position)));
        orderRepository.save(order.orElseThrow());
        return order.orElseThrow();
    }
}
