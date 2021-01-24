package verma.sparsh.petclinic.services.map;

import verma.sparsh.petclinic.model.Owner;
import verma.sparsh.petclinic.services.CrudServices;

import java.util.Set;


public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements CrudServices<Owner, Long> {
    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(),object);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
    super.deleteById(id);
    }
}
