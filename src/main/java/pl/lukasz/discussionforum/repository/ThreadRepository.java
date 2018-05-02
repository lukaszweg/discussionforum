package pl.lukasz.discussionforum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasz.discussionforum.entity.Thread;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {
}
