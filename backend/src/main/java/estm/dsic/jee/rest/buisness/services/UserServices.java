package estm.dsic.jee.rest.buisness.services;

import java.util.List;

import estm.dsic.jee.rest.buisness.interfaces.IUser;
import estm.dsic.jee.rest.dao.UserDao;
import estm.dsic.jee.rest.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UserServices implements IUser {
    @Inject
    UserDao userDao;

    @Override
    public User authentification(User user) {
        return userDao.auth(user);
    }

  
    @Override
    public boolean validate(User user) {
        return userDao.validate(user);
    }

  
    @Override
    public void insert(User user) {
        userDao.create(user);
    }

    @Override
    public void delete(User user ) {
       userDao.delete(user);
    }

    @Override
    public User find(User user) {
        return userDao.find(user);
    }
   
    @Override
    public List<User> all() {
        return userDao.all();
    }

      @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public int nbrContact(User user) {
      return userDao.nbrContact(user);
    }


    // @Override
    // public void logout() {
    //     userDao.logout();
    // }
    
}
