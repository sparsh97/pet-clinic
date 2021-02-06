package verma.sparsh.petclinic.services.map;

import org.springframework.stereotype.Service;
import verma.sparsh.petclinic.model.PetType;
import verma.sparsh.petclinic.services.PetTypeService;

import java.util.Set;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType,Long> implements PetTypeService {
    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public PetType findById(Long id) {
        return null;
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
