package my_first_big_project.service;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.Category;
import my_first_big_project.entity.Product;
import my_first_big_project.entity.Value;
import my_first_big_project.repositories.CategoryRepository;
import my_first_big_project.repositories.ProductRepository;
import my_first_big_project.repositories.ValueRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ValueRepository valueRepository;

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public List<Product> productList() {
        return productRepository.findAll();
        // Sort sort = Sort.by(Sort.Order.asc("idCategory.name"));
    }

    public Product add_product(Long category, String name, Integer price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setIdCategory(categoryRepository.findById(category).orElseThrow());
        productRepository.save(product);
        return product;
    }

    public Product find_product(Long id){
        return productRepository.findById(id).orElseThrow();
    }
    public List<Value> find_value(Long id){
        Product product = new Product();
        product.setId(id);
        return valueRepository.findByIdProduct(product);
    }
}