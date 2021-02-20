package verma.sparsh.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import verma.sparsh.petclinic.model.*;
import verma.sparsh.petclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private  final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count= petTypeService.findAll().size();
        if(count == 0) {
            loadDataOnStart();
        }
    }

    private void loadDataOnStart() {
        PetType dog = new PetType();
        dog.setName("Shiro");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("billi");
        PetType saveCatPetType = petTypeService.save(cat);

        System.out.println("PetType Loaded.........");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology=specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery=specialityService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Sparsh");
        owner1.setLastName("Verma");
        owner1.setAddress("Varanasi, India");
        owner1.setCity("Varanasi");
        owner1.setTelephone("123456789");

        Pet sparshPet = new Pet();
        sparshPet.setPetType(saveDogPetType);
        sparshPet.setOwner(owner1);
        sparshPet.setBirthDate(LocalDate.now());
        sparshPet.setName("Doggy");
        owner1.getPets().add(sparshPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Chhavinder");
        owner2.setLastName("Yadav");
        owner2.setAddress("Varanasi, India");
        owner2.setCity("Varanasi");
        owner2.setTelephone("987456321");

        Pet chhavinderPet = new Pet();
        chhavinderPet.setPetType(saveCatPetType);
        chhavinderPet.setOwner(owner2);
        chhavinderPet.setBirthDate(LocalDate.now());
        chhavinderPet.setName("CatName");
        owner2.getPets().add(chhavinderPet);
        ownerService.save(owner2);

        Visit catVisit= new Visit();
        catVisit.setPet(chhavinderPet);
        catVisit.setDescription("Chhavinder Pet have some virlal health issue");
        catVisit.setDate(LocalDate.now());
         visitService.save(catVisit);
        System.out.println("Loaded owners...........");

        Vet net1 = new Vet();
        net1.setId(1L);
        net1.setFirstName("Abhishek");
        net1.setLastName("Abhishek");
        net1.getSpecialities().add(savedRadiology);
        vetService.save(net1);

        Vet net2 = new Vet();
        net2.setId(2L);
        net2.setFirstName("Jeewan");
        net2.setLastName("Jeewan");
        net2.getSpecialities().add(savedSurgery);
        vetService.save(net2);

        System.out.println("Vet Loaded.........");
    }
}
