package pl.lukasz.discussionforum.service.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Post;
import pl.lukasz.discussionforum.entity.Role;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.PostRepository;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.PostService;
import pl.lukasz.discussionforum.service.RoleService;
import pl.lukasz.discussionforum.service.ThreadService;
import pl.lukasz.discussionforum.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ThreadService threadService;
    private UserService userService;
    private RoleService roleService;

    public PostServiceImpl(PostRepository postRepository, ThreadService threadService, UserService userService, RoleService roleService) {
        this.postRepository = postRepository;
        this.threadService = threadService;
        this.userService = userService;
        this.roleService = roleService;
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

    @Override
    public void deletePost(Long postId, Authentication authentication) {
        String username = postRepository.findById(postId).get().getUserPost().getUsername();
        String auth = authentication.getName();
        User admin = userService.findByUsername(auth);
        Role role = roleService.findByName("ADMIN");
        if(username.equals(auth) || admin.getRoles().contains(role)){
            postRepository.deleteById(postId);
        }
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent())
        {
            return  post.get();
        }

        return null;
    }

    @Override
    public Post presaveEdited(Post post, Long postId, Long threadId, Authentication authentication) {
        post.setId(postId);
        post.setUserPost(userService.findByUsername(authentication.getName()));
        post.setThreadPost(threadService.findById(threadId));
        post.setCreateDate(LocalDateTime.now());
        return postRepository.save(post);
    }
}
