package verma.sparsh.petclinic.services;

import java.util.Set;
/*
* Common Crud Service
* */
public interface CrudServices<T,ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
