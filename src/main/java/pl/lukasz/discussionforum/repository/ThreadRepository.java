package pl.lukasz.discussionforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasz.discussionforum.entity.Thread;

import java.util.List;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    List<Thread> findAllByOrderByCreateDateDesc();
}
