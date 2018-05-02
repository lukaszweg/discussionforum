package pl.lukasz.discussionforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lukasz.discussionforum.repository.ThreadRepository;

@Controller
@RequestMapping(value = "")
public class HomeController {

    private ThreadRepository threadRepository;

    public HomeController(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("threads", threadRepository.findAll());
        return "home";
    }


}
