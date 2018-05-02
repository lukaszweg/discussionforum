package pl.lukasz.discussionforum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasz.discussionforum.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
