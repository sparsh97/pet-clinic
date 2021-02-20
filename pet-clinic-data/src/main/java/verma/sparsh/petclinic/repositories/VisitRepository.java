package verma.sparsh.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import verma.sparsh.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
