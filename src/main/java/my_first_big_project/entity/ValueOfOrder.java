package my_first_big_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "values_of_orders")
@Getter
@Setter
@NoArgsConstructor
public class ValueOfOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order idOrder;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @Column(name = "id_amount")
    private Integer amount;
}