package verma.sparsh.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import verma.sparsh.petclinic.services.OwnerService;

@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners", "/owners/index", "/owners/index.html"})
    public String listOfOwner(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
