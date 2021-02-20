package verma.sparsh.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import verma.sparsh.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType,Long> {
}
