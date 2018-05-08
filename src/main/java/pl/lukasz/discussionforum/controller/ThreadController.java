package pl.lukasz.discussionforum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lukasz.discussionforum.entity.Post;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.service.PostService;
import pl.lukasz.discussionforum.service.ThreadService;

@Controller
public class ThreadController {

    private ThreadService threadService;
    private PostService postService;

    public ThreadController(ThreadService threadService, PostService postService) {
        this.threadService = threadService;
        this.postService = postService;
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
        model.addAttribute("allPosts", postService.findAllByThreadAndOrderByCreateDate(threadId));
        model.addAttribute("post", new Post());
        return "thread";
    }
    @RequestMapping(value = "/threads/{threadId}/delete", method = RequestMethod.GET)
    public String deleteThread(@PathVariable("threadId") Long threadId, Authentication authentication) {
        threadService.delete(threadId, authentication);
        return "redirect:/threads";
    }
    /** todo
     * Edytowanie postu
     */

    @RequestMapping(value = "/threads/{threadId}", method = RequestMethod.POST)
    public String addPost(@PathVariable("threadId") Long threadId,@ModelAttribute("post") Post post, Authentication authentication) {
        postService.precreate(post, threadId, authentication);
        return "redirect:/threads/" + threadId;
    }

    @RequestMapping(value = "/threads/{threadId}/delete/{postId}")
    public String addPost(@PathVariable("threadId") Long threadId,@PathVariable("postId") Long postId,Authentication authentication) {
        postService.deletePost(postId, authentication);
        return "redirect:/threads/" + threadId;
    }


    }

