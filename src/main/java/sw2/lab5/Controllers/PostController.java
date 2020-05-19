package sw2.lab5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sw2.lab5.Entity.Post;
import sw2.lab5.Repository.PostRepository;

import java.util.Optional;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping(value = {"", "/", "list"})
    public String listarEmpleados(Model model) {
        model.addAttribute("listaPost", postRepository.findAll());

        return "/index";
    }

    @GetMapping("/edit")
    public String editarPost(Model model, @RequestParam("id") int id) {
        Optional<Post> optional = postRepository.findById(id);

        if (optional.isPresent()) {
            model.addAttribute("post", optional.get());

            return "post/form";
        } else {
            return "redirect:/list";
        }

    }
    @PostMapping("/save")
    public String guardarNuevoTransportista(@ModelAttribute("post") Post post,
                                            RedirectAttributes attr) {
        if (post.getId_post() == 0) {
            attr.addFlashAttribute("msg", "Usuario creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Usuario actualizado exitosamente");
        }
        postRepository.save(post);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    public String borrarPost(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Post> optPost = postRepository.findById(id);

        if (optPost.isPresent()) {
            postRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Transportista borrado exitosamente");
        }
        return "redirect:/list";

    }

}
