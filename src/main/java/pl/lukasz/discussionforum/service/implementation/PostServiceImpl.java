package pl.lukasz.discussionforum.service.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Post;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.PostRepository;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.PostService;
import pl.lukasz.discussionforum.service.ThreadService;
import pl.lukasz.discussionforum.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ThreadService threadService;
    private UserService userService;

    public PostServiceImpl(PostRepository postRepository, ThreadService threadService, UserService userService) {
        this.postRepository = postRepository;
        this.threadService = threadService;
        this.userService = userService;
    }

    @Override
    public Post precreate(Post post, Long threadId, Authentication authentication) {
       Thread thread = threadService.findById(threadId);
       User user = userService.findByUsername(authentication.getName());
       post.setCreateDate(LocalDateTime.now());
       post.setThreadPost(thread);
       post.setUserPost(user);
       return postRepository.save(post);
    }

    @Override
    public List<Post> findAllByThreadAndOrderByCreateDate(Long threadId) {
        Thread thread = threadService.findById(threadId);
        return postRepository.findAllByThreadPostOrderByCreateDateAsc(thread);
    }
}
