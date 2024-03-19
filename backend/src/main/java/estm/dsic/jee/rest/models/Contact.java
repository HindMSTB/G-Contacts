package estm.dsic.jee.rest.models;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import jakarta.json.bind.annotation.JsonbProperty;

import estm.dsic.jee.rest.models.Contact;
import jakarta.persistence.*;

@Entity
@Table(name="contact")
public class Contact  implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "telephone")
    private String telephone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "adresse")
    private String adresse;
    
    @ManyToOne
    @JoinColumn(name="id_user")
    @JsonbProperty
    private User user;

  
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}