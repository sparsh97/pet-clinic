package verma.sparsh.petclinic.services.map;

import verma.sparsh.petclinic.model.Pet;
import verma.sparsh.petclinic.services.CrudServices;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudServices<Pet,Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return save(object.getId(),object);
    }

    @Override
    public void delete(Pet object) {
    super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
