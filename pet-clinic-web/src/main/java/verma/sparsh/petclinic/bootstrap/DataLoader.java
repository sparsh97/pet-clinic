package verma.sparsh.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import verma.sparsh.petclinic.model.Owner;
import verma.sparsh.petclinic.model.Vet;
import verma.sparsh.petclinic.services.OwnerService;
import verma.sparsh.petclinic.services.VetService;
import verma.sparsh.petclinic.services.map.OwnerServiceMap;
import verma.sparsh.petclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService= new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1= new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Sparsh");
        owner1.setLastName("Verma");
        ownerService.save(owner1);

        Owner owner2= new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Chhavinder");
        owner2.setLastName("Yadav");
        ownerService.save(owner2);

        System.out.println("Loaded owners...........");

        Vet net1= new Vet();
        net1.setId(1L);
        net1.setFirstName("Abhishek");
        net1.setLastName("Abhishek");
        vetService.save(net1);

        Vet net2= new Vet();
        net2.setId(2L);
        net2.setFirstName("Jeewan");
        net2.setLastName("Jeewan");
        vetService.save(net2);

        System.out.println("Vet Loaded.........");

    }
}
