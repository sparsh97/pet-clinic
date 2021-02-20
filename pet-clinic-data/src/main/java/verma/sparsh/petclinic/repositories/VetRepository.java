package verma.sparsh.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import verma.sparsh.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet,Long> {
}
