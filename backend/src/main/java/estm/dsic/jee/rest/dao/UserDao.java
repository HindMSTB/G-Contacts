package estm.dsic.jee.rest.dao;
import java.util.ArrayList;
import java.util.List;

import estm.dsic.jee.rest.models.User;
import jakarta.enterprise.context.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Named
@RequestScoped
@Transactional
public class UserDao implements Reposistory<User,String>{


    @PersistenceContext(name = "gestioncontact")
    EntityManager em;

    @Inject
    HttpSession session;

    public User auth(User user) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            User authenticatedUser = query.getSingleResult();

            if (authenticatedUser != null) {
                session.setAttribute("authenticatedUser", authenticatedUser);
            }

            return authenticatedUser;
        } catch (NoResultException e) {
            return null;
        }
    }

    // public void logout() {
      
    //     if (session != null) {
    //         session.invalidate();
    //     }
    // }
 
@Override
public void create(User entity) {
    try {
        em.persist(entity);
    } catch (Exception e) {
           e.printStackTrace();
    }
}

@Override
public User find(User entity) {
    try {
     return em.find(User.class, entity.getId());
    } catch (NoResultException e) {
        return null; 
    }

}

   
public List<User> all() {
    try {
       
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
     
        return query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return null; 
    }
}

    
@Override
public void delete(User entity) {
    try {
        em.remove(em.find(User.class, entity.getId()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@Override
public User update(User entity) {
    try {
       
        User updatedUser = em.merge(entity);
        return updatedUser;

    } catch (Exception e) {
        e.printStackTrace();
        return null; 
    }
}


   

public boolean validate(User user) {
    if (user != null) {
        try {
            TypedQuery<Void> query = em.createQuery("UPDATE User u SET u.validate = 1 WHERE u.id = :id", Void.class);
            query.setParameter("id", user.getId());
            int updatedCount = query.executeUpdate();
            return updatedCount > 0; 
        } catch (Exception e) {
            e.printStackTrace();
            return false; 
        }
    } else {
        return false; 
    }
}

public int nbrContact(User user) {
    try {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Contact c WHERE c.user = :user", Long.class);
        query.setParameter("user", user);
        return query.getSingleResult().intValue();
    } catch (NoResultException e) {
        return 0;
    }
}


}