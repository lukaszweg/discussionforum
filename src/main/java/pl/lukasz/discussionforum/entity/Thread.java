package pl.lukasz.discussionforum.entity;

import org.springframework.format.annotation.DateTimeFormat;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userThread;

    @OneToMany(mappedBy = "threadPost", cascade = CascadeType.ALL)
    private List<Post> post;

    public Thread() {}

    public Thread(@NotNull String threadName, @NotNull String description, User userThread, List<Post> post) {
        this.threadName = threadName;
        this.description = description;
        this.userThread = userThread;
        this.post = post;
    }

    public Thread(@NotNull String threadName, @NotNull String description, User userThread) {
        this.threadName = threadName;
        this.description = description;
        this.userThread = userThread;
    }

    public Thread(@NotNull String threadName, @NotNull String description, LocalDate createDate, User userThread) {
        this.threadName = threadName;
        this.description = description;
        this.createDate = createDate;
        this.userThread = userThread;
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
