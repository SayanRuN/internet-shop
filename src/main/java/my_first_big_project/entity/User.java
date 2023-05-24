package my_first_big_project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my_first_big_project.enumuration.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private Role role;

    private String login;
    private String password;
    private String email;
    private String number;
    private String name;

    @Column(name = "sur_name")
    private String surName;

    public User(String login, String email, String number, String name, String surName, LocalDateTime regDate) {
        this.login = login;
        this.email = email;
        this.number = number;
        this.name = name;
        this.surName = surName;
        this.regDate = regDate;
    }

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "idUser")
    private List<Order> order;

    @OneToMany(mappedBy = "idUser")
    private List<Comment> comment;

    @OneToMany(mappedBy = "user")
    private List<Basket> basket;
}