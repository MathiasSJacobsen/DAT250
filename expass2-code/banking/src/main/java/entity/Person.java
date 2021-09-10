package entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ManyToMany
    private List<Address> address;

    @OneToMany
    private List<CreditCard> creditCardList;

}
