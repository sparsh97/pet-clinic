package verma.sparsh.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import verma.sparsh.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
