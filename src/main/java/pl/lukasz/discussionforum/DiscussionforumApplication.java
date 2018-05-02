package pl.lukasz.discussionforum;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;
import pl.lukasz.discussionforum.repository.PostRepository;
import pl.lukasz.discussionforum.repository.ThreadRepository;
import pl.lukasz.discussionforum.repository.UserRepository;

@SpringBootApplication
public class DiscussionforumApplication implements CommandLineRunner {
	private PostRepository postRepository;
	private ThreadRepository threadRepository;
	private UserRepository userRepository;

	public DiscussionforumApplication(PostRepository postRepository, ThreadRepository threadRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.threadRepository = threadRepository;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DiscussionforumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Name1", "Last1");
		User user2 = new User("Name2", "Last2");
		userRepository.save(user1);
		userRepository.save(user2);
		Thread thread1 = new Thread("name1", "zawartosc1", user1);
		Thread thread2 = new Thread("name2", "zawartosc2", user2);
		threadRepository.save(thread1);
		threadRepository.save(thread2);

	}
}
