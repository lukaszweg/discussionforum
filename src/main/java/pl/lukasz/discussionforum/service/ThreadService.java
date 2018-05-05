package pl.lukasz.discussionforum.service;

import org.springframework.security.core.Authentication;
import pl.lukasz.discussionforum.entity.Thread;

import java.util.List;

public interface ThreadService {
    List<Thread> findAllAndOrderByCreationType();

    Thread presave(Thread thread, Authentication authentication);
}
