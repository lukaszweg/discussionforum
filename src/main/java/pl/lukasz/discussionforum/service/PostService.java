package pl.lukasz.discussionforum.service;

import org.springframework.security.core.Authentication;
import pl.lukasz.discussionforum.entity.Post;
import pl.lukasz.discussionforum.entity.Thread;

import java.util.List;

public interface PostService {
    Post precreate(Post post, Long threadId, Authentication authentication);

    List<Post> findAllByThreadAndOrderByCreateDate(Long threadId);

    void deletePost(Long postId, Authentication authentication);

    Post findById(Long id);

    Post presaveEdited(Post post, Long postId, Long threadId, Authentication authentication);

}
