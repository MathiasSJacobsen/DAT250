package entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Data
public class Pincode {

    private int pincode;
    private int count;


}
