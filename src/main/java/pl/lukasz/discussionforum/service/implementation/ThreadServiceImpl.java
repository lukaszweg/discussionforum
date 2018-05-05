package pl.lukasz.discussionforum.service.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.ThreadService;
import pl.lukasz.discussionforum.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService {

    private ThreadRepository threadRepository;
    private UserService userService;

    public ThreadServiceImpl(ThreadRepository threadRepository, UserService userService) {
        this.threadRepository = threadRepository;
        this.userService = userService;
    }

    @Override
    public List<Thread> findAllAndOrderByCreationType() {
        return threadRepository.findAllByOrderByCreateDateDesc();
    }

    @Override
    public Thread presave(Thread thread, Authentication authentication) {
        LocalDate getDate = LocalDate.now();
        thread.setCreateDate(getDate);
        thread.setUserThread(userService.findByUsername(authentication.getName()));
        return threadRepository.save(thread);
    }
}

