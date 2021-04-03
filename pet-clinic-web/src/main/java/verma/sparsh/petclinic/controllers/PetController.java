package verma.sparsh.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import verma.sparsh.petclinic.model.Owner;
import verma.sparsh.petclinic.model.Pet;
import verma.sparsh.petclinic.model.PetType;
import verma.sparsh.petclinic.services.OwnerService;
import verma.sparsh.petclinic.services.PetService;
import verma.sparsh.petclinic.services.PetTypeService;

import java.util.Collection;
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM="pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    private Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    private Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    private void initOwnerBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreateForm(Owner owner, Model model){
        Pet pet= new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult bindingResult, ModelMap model){
        if(StringUtils.hasLength(pet.getName()) && pet.isNew()&& owner.getPet(pet.getName(), true)!=null){
            bindingResult.rejectValue("name","duplicate","already exists");
        }
        owner.getPets().add(pet);
        if(bindingResult.hasErrors()){
            model.put("pet",pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }else{
            petService.save(pet);
            return "redirect:/owners/"+ owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model){
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet, BindingResult bindingResult, Owner owner, Model model){
        if(bindingResult.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet",pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else{
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/"+ owner.getId();
        }
    }
}
