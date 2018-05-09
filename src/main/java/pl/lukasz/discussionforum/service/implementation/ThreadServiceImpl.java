package pl.lukasz.discussionforum.service.implementation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.lukasz.discussionforum.entity.Role;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.RoleService;
import pl.lukasz.discussionforum.service.ThreadService;
import pl.lukasz.discussionforum.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ThreadServiceImpl implements ThreadService {

    private ThreadRepository threadRepository;
    private UserService userService;
    private RoleService roleService;


    public ThreadServiceImpl(ThreadRepository threadRepository, UserService userService, RoleService roleService) {
        this.threadRepository = threadRepository;
        this.userService = userService;
        this.roleService = roleService;
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

    @Override
    public Optional<Thread> findOne(Long threadId) {
        return threadRepository.findById(threadId);
    }

    @Override
    public void delete(Long threadId, Authentication authentication) {
        String auth = authentication.getName();
        String user = threadRepository.findById(threadId).get().getUserThread().getUsername();
        User admin = userService.findByUsername(auth);
        Role role = roleService.findByName("ADMIN");
        if(auth.equals(user) || admin.getRoles().contains(role)) {
            threadRepository.deleteById(threadId);
        }
    }

    @Override
    public Thread findById(Long threadId) {
        Optional<Thread> thread = threadRepository.findById(threadId);
        if(thread.isPresent())
        {
            return thread.get();
        }

        return null;

    }

    @Override
    public Thread presaveEdited(Thread thread, Long threadId, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        thread.setUserThread(user);
        thread.setId(threadId);
        thread.setCreateDate(LocalDate.now());
        return threadRepository.save(thread);
    }
}

