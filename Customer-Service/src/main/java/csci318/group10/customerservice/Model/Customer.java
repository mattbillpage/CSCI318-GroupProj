package csci318.group10.customerservice.Model;

import jakarta.persistence.*;

//Customer class that provides the Schema and Associations for H2 Database
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int ID;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String emailID;

    @Column
    private String password;

    public Customer() {} //Empty constructor


    //Getters and Setters
    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String email) {
        this.emailID = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}