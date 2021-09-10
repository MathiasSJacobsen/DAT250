package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class CreditCard {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Long number;
    private float balance;
    private float limit;
    @Embedded
    private Pincode pincode;

    @ManyToOne
    private Bank bank;

    @ManyToOne
    private Person owner;

}
