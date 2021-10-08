package entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Bank {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<CreditCard> creditCardList;


}
