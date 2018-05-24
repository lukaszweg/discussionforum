package pl.lukasz.discussionforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasz.discussionforum.entity.Thread;
import pl.lukasz.discussionforum.entity.User;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findAllByOrderByCreateDateDesc();
    List<Thread> findThreadsByUserThread(User user);
}
