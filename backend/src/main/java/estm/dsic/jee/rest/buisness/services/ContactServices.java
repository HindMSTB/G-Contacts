package estm.dsic.jee.rest.buisness.services;

import java.util.List;

import estm.dsic.jee.rest.buisness.interfaces.IContact;
import estm.dsic.jee.rest.dao.ContactDao;
import estm.dsic.jee.rest.models.Contact;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ContactServices implements IContact {

    @Inject
    ContactDao contactDao;

    @Override
    public Contact addContact(Contact contact) {
        contactDao.create(contact);
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return contactDao.update(contact);
    }

    @Override
    public List<Contact> all(int idUser) {
        return contactDao.all(idUser);
    }


    @Override
    public Contact find(Contact contact) {
        return contactDao.find(contact);
    }

   
    
}
