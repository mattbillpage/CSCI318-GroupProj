package csci318.group10.cartservice.domain.models;

import jakarta.persistence.*;

//Cart class that provides the Schema and Associations for H2 Database
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private int ID;

    // TODO : Create parameters for carts

    public Cart() {} //Empty constructor


    //Getters and Setters

    // TODO : Getters and Setters for carts

}