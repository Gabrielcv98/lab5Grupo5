package sw2.lab5.Controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import sw2.lab5.Entity.Users;
import sw2.lab5.Repository.UserRepository;

import javax.servlet.http.HttpSession;
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/loginForm")
    public String loginForm(){
        return "login/form";
    }

    @GetMapping(value = "/redirectByRole")
    public String redirectByRole(Authentication auth, HttpSession session) {
        String rol = "";
        for (GrantedAuthority role : auth.getAuthorities()) {
            rol = role.getAuthority();
            break;
        }
        String username = auth.getName();

        Users usuario = userRepository.findByEmail(username);

        session.setAttribute("usuario",usuario);



        if (rol.equals("admin")) {
            return "redirect:/index/";
        } else {
            return "redirect:/employee/";
        }
    }

}
