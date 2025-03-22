package community.community_spring.comment.repository;

import community.community_spring.comment.domain.PostComment;
import community.community_spring.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPostCommentRepository extends JpaRepository<PostComment, Long> {

    // 특정 게시글(postId)에 달린 댓글 전부 조회
    List<PostComment> findByPostId(Long postId);

    // 특정 유저(userId)가 작성한 댓글 조회도 가능
    List<PostComment> findByUserId(Long userId);
}
