package verma.sparsh.petclinic.services;

import verma.sparsh.petclinic.model.Owner;


public interface OwnerService extends CrudServices<Owner, Long> {
    Owner findByLastName(String lastName);

}
