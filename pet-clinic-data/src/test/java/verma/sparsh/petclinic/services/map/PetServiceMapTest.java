package verma.sparsh.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verma.sparsh.petclinic.model.Pet;
import verma.sparsh.petclinic.services.PetService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetServiceMapTest {

    PetServiceMap petServiceMap;
    PetService petService;
    final Long id=1L;
    @BeforeEach
    void setUp() {
        petServiceMap= new PetServiceMap();
        petServiceMap.save(Pet.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet= petServiceMap.findAll();
        assertEquals(id, petSet.size());

    }

    @Test
    void findById() {
        Pet pet= petServiceMap.findById(id);
        assertEquals(id, pet.getId());
    }

    @Test
    void saveExistingId() {
        Long id=2L;
        Pet pet= Pet.builder().id(id).build();
        Pet pet1= petServiceMap.save(pet);

        assertEquals(id, pet1.getId());
    }

    @Test
    void saveNoId() {
        Pet pet= petServiceMap.save(Pet.builder().build());
        assertNotNull(pet);
        assertNotNull(pet.getId());
    }

    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(id));
        assertEquals(0,petServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(id);
        assertEquals(0,petServiceMap.findAll().size());
    }
}