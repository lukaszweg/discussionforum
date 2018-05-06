package pl.lukasz.discussionforum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/threads/" + thread.getId() ;
    }

    @RequestMapping(value = "/threads/{threadId}", method = RequestMethod.GET)
    public String getThread(@PathVariable("threadId") Long threadId, Model model) {
        model.addAttribute("findThread", threadService.findOne(threadId));
        return "thread";
    }
    @RequestMapping(value = "/threads/{threadId}/delete", method = RequestMethod.GET)
    public String deleteThread(@PathVariable("threadId") Long threadId, Authentication authentication) {
        threadService.delete(threadId, authentication);
        return "redirect:/threads";
    }



    /* todo
    wczytywanie danego tematu
    usuwanie tematu
    edytowanie tematu
     */
}
