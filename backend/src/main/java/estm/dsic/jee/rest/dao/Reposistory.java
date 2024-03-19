package estm.dsic.jee.rest.dao;

import java.util.List;

import estm.dsic.jee.rest.models.User;

public interface Reposistory<T,I> {
    

    void create(T entity);
    T find (T entity);
    void delete(T entity );
    T update(T entity);
    //  List<T> all();

}
