package estm.dsic.jee.rest.buisness.interfaces;

import java.util.List;

import estm.dsic.jee.rest.models.User;

public interface IUser {
    User authentification(User user);
    // Boolean subscription(User user);
    boolean validate(User user);
    void insert(User user);
    void delete(User user);
    User find (User user);
    List<User> all();
    User update(User user);
    int nbrContact(User user);
    // void logout();
}

