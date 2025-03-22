package community.community_spring.post.repository;

import community.community_spring.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Post, Long> {
}
