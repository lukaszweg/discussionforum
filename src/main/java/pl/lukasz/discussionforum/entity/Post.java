package pl.lukasz.discussionforum.entity;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Length(max = 300, min = 5, message = "od 5 do 300 znaków!")
    @Column(nullable = false)
    private String content;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createDate;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User userPost;

    @ManyToOne()
    @JoinColumn(name = "thread_id")
    private Thread threadPost;

    public Post() {}

    public Post(@NotNull String content, User userPost, Thread threadPost) {
        this.content = content;
        this.userPost = userPost;
        this.threadPost = threadPost;
    }

    public Post(@NotNull String content, @NotNull LocalDateTime createDate, User userPost, Thread threadPost) {
        this.content = content;
        this.createDate = createDate;
        this.userPost = userPost;
        this.threadPost = threadPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserPost() {
        return userPost;
    }

    public void setUserPost(User userPost) {
        this.userPost = userPost;
    }

    public Thread getThreadPost() {
        return threadPost;
    }

    public void setThreadPost(Thread threadPost) {
        this.threadPost = threadPost;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userPost=" + userPost +
                ", threadPost=" + threadPost +
                '}';
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
