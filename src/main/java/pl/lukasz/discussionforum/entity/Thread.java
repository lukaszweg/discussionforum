package pl.lukasz.discussionforum.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String threadName;

    @Lob
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userThread;

    @OneToMany(mappedBy = "threadPost")
    private List<Post> post;

    public Thread() {}

    public Thread(@NotNull String threadName, @NotNull String description, User userThread, List<Post> post) {
        this.threadName = threadName;
        this.description = description;
        this.userThread = userThread;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserThread() {
        return userThread;
    }

    public void setUserThread(User userThread) {
        this.userThread = userThread;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "id=" + id +
                ", threadName='" + threadName + '\'' +
                ", description='" + description + '\'' +
                ", userThread=" + userThread +
                ", post=" + post +
                '}';
    }
}
