package verma.sparsh.petclinic.services;

import verma.sparsh.petclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudServices<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
