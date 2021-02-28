package verma.sparsh.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verma.sparsh.petclinic.model.PetType;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeServiceMapTest {

    PetTypeServiceMap petTypeServiceMap;
    final Long petTypeId= 1L;
    @BeforeEach
    void setUp(){
        petTypeServiceMap= new PetTypeServiceMap();
        petTypeServiceMap.save(PetType.builder().id(petTypeId).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet= petTypeServiceMap.findAll();
        assertEquals(petTypeId, petTypeSet.size());
    }

    @Test
    void findById() {

        PetType petType= petTypeServiceMap.findById(petTypeId);
        assertEquals(petTypeId, petType.getId());
    }

    @Test
    void saveExistingId() {
        Long id=2L;
        PetType petType= PetType.builder().id(id).build();
        PetType petType1= petTypeServiceMap.save(petType);

        assertEquals(id, petType1.getId());

    }

    @Test
    void saveNoId() {

        PetType petType= petTypeServiceMap.save(PetType.builder().build());
        assertNotNull(petType);
        assertNotNull(petType.getId());
    }

    @Test
    void delete() {
        petTypeServiceMap.delete(petTypeServiceMap.findById(petTypeId));
        assertEquals(0,petTypeServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeServiceMap.deleteById(petTypeId);
        assertEquals(0,petTypeServiceMap.findAll().size());
    }
}