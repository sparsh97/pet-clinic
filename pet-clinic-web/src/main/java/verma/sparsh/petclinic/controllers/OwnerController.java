package verma.sparsh.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import verma.sparsh.petclinic.model.Owner;
import verma.sparsh.petclinic.services.OwnerService;

import java.util.List;

@Controller
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM= "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
//    public String listOfOwner(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @RequestMapping("/owners/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model){
        if(owner.getLastName() == null){
            owner.setLastName("");
        }
        List<Owner> results= ownerService.findAllByLastNameLike("%"+ owner.getLastName() + "%");
        if(results.isEmpty()){
            bindingResult.rejectValue("lastName","notFound","notFound");
            return "owners/findOwners";
        } else if(results.size() ==1){
            owner= results.get(0);
            return "redirect:/owners/"+ owner.getId();
        } else{
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreationForm(Owner owner, BindingResult results){
        if(results.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else{
            Owner saveOwner= ownerService.save(owner);
            return "redirect:/owners/"+ saveOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(Owner owner, BindingResult bindingResult, @PathVariable("ownerId") Long ownerId){
        if(bindingResult.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else{
            owner.setId(ownerId);
            Owner saveOwner= ownerService.save(owner);
            return "redirect:/owners/"+saveOwner.getId();
        }
    }
}
