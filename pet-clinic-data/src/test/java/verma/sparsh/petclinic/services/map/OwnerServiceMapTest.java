package verma.sparsh.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verma.sparsh.petclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    private final Long ownerSize= 1L;
    final String firstName= "Sparsh";
    @BeforeEach
    void setUp(){
        ownerServiceMap= new OwnerServiceMap(new PetServiceMap(),new PetTypeServiceMap());
        ownerServiceMap.save(Owner.builder().id(1l).lastName(firstName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet= ownerServiceMap.findAll();
        assertEquals(ownerSize,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner= ownerServiceMap.findById(ownerSize);
        assertEquals(ownerSize,owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id=2L;

        Owner owner2= Owner.builder().id(id).build();
        Owner savedOwner= ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {

        Owner savedOwner= ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerSize));
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerSize);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner= ownerServiceMap.findByLastName(firstName);
        assertNotNull(owner);
        assertEquals(ownerSize,owner.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner= ownerServiceMap.findByLastName("nnnnn");
        assertNull(owner);
    }
}