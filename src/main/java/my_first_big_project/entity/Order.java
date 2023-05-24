package my_first_big_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my_first_big_project.enumuration.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User idUser;

    @Enumerated
    private Status status;

    private String street;
    private Integer house;
    private Integer flat;

    @Column(name = "date_order")
    private LocalDateTime dateOrder;

    @OneToMany(mappedBy = "idOrder")
    private List<ValueOfOrder> valueOfOrder;
}