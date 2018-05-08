package pl.lukasz.discussionforum.service;

import org.springframework.security.core.Authentication;
import pl.lukasz.discussionforum.entity.Thread;

import java.util.List;
import java.util.Optional;

public interface ThreadService {
    List<Thread> findAllAndOrderByCreationType();

    Thread presave(Thread thread, Authentication authentication);

    Optional<Thread> findOne(Long threadId);

    void delete(Long threadId, Authentication authentication);

    Thread findById(Long threadId);


//    Optional<Thread> update(Long threadId, Authentication authentication);
}
