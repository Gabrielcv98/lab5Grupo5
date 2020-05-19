package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sw2.lab5.Entity.Users;
import sw2.lab5.Repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class ControllerUser {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = {"", "/lista"})
    public String lista (Model model){

        model.addAttribute("listaUser", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/editar")
    public String editar (@ModelAttribute("user") Users users, Model model,
                          @RequestParam("id") int id){

        Optional<Users> opt =   userRepository.findById(id);
        if (opt.isPresent()){
            users = opt.get();
            model.addAttribute("user", users);
            return "user/editar";
        }else {
            return "redirect:/user";
        }

    }

    @GetMapping("/borrar")
    public String borrar (Model model, @RequestParam("id") int id){

        Optional<Users> opt = userRepository.findById(id);
        if(opt.isPresent()){
            userRepository.deleteById(id);
        }

        return "redirect:/user";
    }


}
