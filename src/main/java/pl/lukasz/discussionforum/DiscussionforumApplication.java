package pl.lukasz.discussionforum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.lukasz.discussionforum.entity.Role;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.PostRepository;
import pl.lukasz.discussionforum.repository.RoleRepository;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;
import pl.lukasz.discussionforum.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DiscussionforumApplication implements CommandLineRunner {
	private RoleRepository roleRepository;
	private PostRepository postRepository;
	private ThreadRepository threadRepository;
	private UserRepository userRepository;
	private RoleService roleService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public DiscussionforumApplication(RoleRepository roleRepository, PostRepository postRepository, ThreadRepository threadRepository, UserRepository userRepository, RoleService roleService) {
		this.roleRepository = roleRepository;
		this.postRepository = postRepository;
		this.threadRepository = threadRepository;
		this.userRepository = userRepository;
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DiscussionforumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "test";



		User user1 = new User("Name1", passwordEncoder.encode(password));
		User user2 = new User("Name2", "Last2");
		userRepository.save(user1);
		userRepository.save(user2);
		Thread thread1 = new Thread("name1", "zawartosc1", user1);
		Thread thread2 = new Thread("name2", "zawartosc2", user2);
		threadRepository.save(thread1);
		threadRepository.save(thread2);

	}
}
