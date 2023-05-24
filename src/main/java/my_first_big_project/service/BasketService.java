package my_first_big_project.service;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Basket;
import my_first_big_project.entity.Product;
import my_first_big_project.entity.User;
import my_first_big_project.repositories.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserService userService;
    private final BasketRepository basketRepository;

    public List<Basket> basketList() {
        User idUser = userService.getCurrentUser();
        basketRepository.findByUser(idUser);
        return basketRepository.findByUser(idUser);
    }

    public Basket add_Product(Long idProduct, Integer quantity) {
        Basket basket = new Basket();
        User idUser = userService.getCurrentUser();
        Product product = new Product();
        product.setId(idProduct);
        basket.setUser(idUser);
        basket.setProduct(product);
        basket.setQuantity(quantity);
        basketRepository.save(basket);
        return basket;
    }

    public Basket deleteProduct(Long idBasket) {
        Basket basket = new Basket();
        basket.setId(idBasket);
        basketRepository.delete(basket);
        return basket;
    }

    public Basket increaseProduct(Long id) {
        Optional<Basket> basket = basketRepository.findById(id);
        basket.ifPresent(basket1 -> basket1.setQuantity(basket1.getQuantity() + 1));
        basketRepository.save(basket.orElseThrow());
        return basket.get();
    }

    public Optional<Basket> decreaseProduct(Long id) {
        Optional<Basket> basket = basketRepository.findById(id);
        basket.ifPresent(basket1 -> basket1.setQuantity(basket1.getQuantity() - 1));
        basketRepository.save(basket.orElseThrow());
        return basket;
    }
}