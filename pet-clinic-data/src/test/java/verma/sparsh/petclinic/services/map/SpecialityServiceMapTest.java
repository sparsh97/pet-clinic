package verma.sparsh.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verma.sparsh.petclinic.model.Speciality;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpecialityServiceMapTest {

    SpecialityServiceMap specialityServiceMap;
    final Long id=1L;

    @BeforeEach
    void setUp(){
        specialityServiceMap=new SpecialityServiceMap();
        specialityServiceMap.save(Speciality.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet= specialityServiceMap.findAll();
        assertEquals(id, specialitySet.size());
    }

    @Test
    void findById() {
        Speciality speciality= specialityServiceMap.findById(id);
        assertEquals(id, speciality.getId());
    }

    @Test
    void saveExistingId() {

        Long id=2L;
        Speciality speciality= Speciality.builder().id(id).build();
        Speciality speciality1= specialityServiceMap.save(speciality);
        assertEquals(id, speciality1.getId());
    }

    @Test
    void saveNoId() {

        Speciality speciality= specialityServiceMap.save(Speciality.builder().build());
//        Speciality speciality1= specialityServiceMap.save(speciality);
        assertNotNull(speciality);
        assertNotNull(speciality.getId());
    }

    @Test
    void delete() {
        specialityServiceMap.delete(specialityServiceMap.findById(id));
        assertEquals(0,specialityServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        specialityServiceMap.deleteById(id);
        assertEquals(0,specialityServiceMap.findAll().size());
    }
}