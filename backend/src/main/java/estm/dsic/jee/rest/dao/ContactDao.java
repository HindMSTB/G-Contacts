package estm.dsic.jee.rest.dao;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import estm.dsic.jee.rest.models.Contact;

@Named
@RequestScoped
@Transactional
public class ContactDao implements Reposistory<Contact, String> {

    @PersistenceContext(name = "gestioncontact")
    EntityManager em;

    
    @Override
    public void create(Contact entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // @Override
    // public void create(Contact entity) {
    //     try {
    //         // Vérification du format de l'email
    //         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    //         if (!entity.getEmail().matches(emailRegex)) {
    //             throw new IllegalArgumentException("Invalid email format");
    //         }

    //         // Vérification du format du numéro de téléphone
    //         String phoneRegex = "\\d{10}"; // Par exemple, un numéro de téléphone à 10 chiffres
    //         if (!entity.getTelephone().matches(phoneRegex)) {
    //             throw new IllegalArgumentException("Invalid phone number format");
    //         }

    //         // Vérification de l'existence de l'email dans la base de données
    //         long count = em.createQuery("SELECT COUNT(c) FROM Contact c WHERE c.email = :email", Long.class)
    //                 .setParameter("email", entity.getEmail())
    //                 .getSingleResult();
    //         if (count > 0) {
    //             throw new IllegalArgumentException("Email already exists");
    //         }

    //         // Persistez l'entité dans la base de données
    //         em.persist(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public Contact find(Contact entity) {
        try {
            return em.find(Contact.class, entity.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Contact entity) {
        try {
            em.remove(em.find(Contact.class, entity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact update(Contact entity) {
        try {
           
            Contact existingContact = em.find(Contact.class, entity.getId());
    
            
            if (existingContact == null) {
                return null; 
            }
    
         existingContact.setNom(entity.getNom());
            existingContact.setTelephone(entity.getTelephone());
            existingContact.setEmail(entity.getEmail());
            existingContact.setAdresse(entity.getAdresse());
    
            return em.merge(existingContact);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public List<Contact> all(int idUser) {
        try {
            TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c WHERE c.user.id = :userId", Contact.class);
            query.setParameter("userId", idUser);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
             return null;
         }

        }


    
}
