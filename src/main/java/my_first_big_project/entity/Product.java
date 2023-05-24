package my_first_big_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category idCategory;

    private String name;
    private Integer price;

    @OneToMany(mappedBy = "idProduct")
    private List<Value> value;

    @OneToMany(mappedBy = "idProduct")
    private List<ValueOfOrder> valueOfOrder;

    @OneToMany(mappedBy = "idProduct")
    private List<Comment> comment;

    @OneToMany(mappedBy = "product")
    private List<Basket> basket;
}