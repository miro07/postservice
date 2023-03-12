package postservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import postservice.entities.Post;

import java.util.List;
@Repository
public interface PostDao extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.address.region = :x")
    List<Post> findPostsByRegion(@Param("x")String region);
    List<Post> findPostsByUserId(Long userId);
}
