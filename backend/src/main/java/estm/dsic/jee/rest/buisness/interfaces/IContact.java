package estm.dsic.jee.rest.buisness.interfaces;

import java.util.List;

import estm.dsic.jee.rest.models.Contact;


public interface IContact {
    Contact addContact(Contact contact );
    void delete(Contact contact);
    Contact updateContact(Contact contact);
    List<Contact> all(int idUser);
    Contact find (Contact contact);
}
