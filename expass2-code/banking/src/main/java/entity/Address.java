package entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private String street;

    @ManyToMany
    private List<Person> person;


}
