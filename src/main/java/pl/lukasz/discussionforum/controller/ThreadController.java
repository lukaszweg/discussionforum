package pl.lukasz.discussionforum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.service.ThreadService;

@Controller
public class ThreadController {

    private ThreadService threadService;

    public ThreadController(ThreadService threadService) {
        this.threadService = threadService;
    }

    @RequestMapping("/threads")
    public String getThreadsAndSortByDate(Model model) {
        model.addAttribute("threads", threadService.findAllAndOrderByCreationType());
        return "threads";
    }

    @RequestMapping(value = "/threads/add", method = RequestMethod.GET)
    public String addThread(Model model) {
        model.addAttribute("newThread", new Thread());
        return "forms/threadForm";
    }

    @RequestMapping(value = "/threads/add", method = RequestMethod.POST)
    public String saveThread(@ModelAttribute("newThread") Thread thread, BindingResult bindingResult, Authentication authentication) {
        if(bindingResult.hasErrors()) {
            return "threadForm";
        }
        threadService.presave(thread, authentication);
        return "redirect:/threads";
    }
}
