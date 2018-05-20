package pl.lukasz.discussionforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.service.PostService;
import pl.lukasz.discussionforum.service.ThreadService;

@Controller
@RequestMapping(value = "")
public class HomeController {

    private ThreadService threadService;
    private PostService postService;

    public HomeController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
    }

    @RequestMapping
    public String home(Model model) {
        model.addAttribute("threads", threadService.findFiveNewestThreads());
        model.addAttribute("posts", postService.findFiveNewestPosts());
        return "home";
    }


}
