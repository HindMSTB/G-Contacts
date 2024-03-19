package estm.dsic.jee.rest.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import estm.dsic.jee.rest.models.User;
import jakarta.persistence.*;


@Entity
@Table(name="user")
public class User  implements Serializable{

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "isAdmin")
    private int isAdmin; 
    
    @Column(name = "validate")
    private int validate;
      
    @OneToMany(mappedBy = "user")
    @JsonbTransient
    private List<Contact> contacts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int isValidate() {
        return validate;
    }

    public void setValidate(int validate) {
        this.validate = validate;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

   /*  public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    } */
}