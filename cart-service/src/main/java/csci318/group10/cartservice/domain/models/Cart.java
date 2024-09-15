package csci318.group10.cartservice.domain.models;

import jakarta.persistence.*;
import csci318.group10.cartservice.domain.models.*;

import java.util.ArrayList;
import java.util.List;

//Cart class that provides the Schema and Associations for H2 Database
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private int ID;

    @Column
    private int userID;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    // TODO : Create parameters for carts

    public Cart() {} //Empty constructor

    public Cart(int userID) {
        this.userID = userID;
    }


    public void emptyItems() {
        items.clear();
    }

    //Getters and Setters

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }


}