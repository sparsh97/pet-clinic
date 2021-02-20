package verma.sparsh.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import verma.sparsh.petclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
