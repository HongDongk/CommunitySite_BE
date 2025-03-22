package community.community_spring.comment.service;

import community.community_spring.comment.domain.PostComment;
import community.community_spring.comment.dto.PostCommentCreateRequest;
import community.community_spring.comment.dto.PostCommentResponse;
import community.community_spring.comment.repository.JpaPostCommentRepository;
import community.community_spring.post.domain.Post;
import community.community_spring.post.repository.JpaPostRepository;
import community.community_spring.user.domain.User;
import community.community_spring.user.repository.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostCommentService {
    private final JpaPostCommentRepository commentRepository;
    private final JpaPostRepository postRepository;
    private final JpaUserRepository userRepository;

    @Autowired
    public PostCommentService(JpaPostCommentRepository commentRepository, JpaPostRepository postRepository, JpaUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 댓글 생성
    public void createComment(Long postId, PostCommentCreateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        PostComment comment = PostComment.builder()
                .post(post)
                .user(user)
                .content(request.getContent())
                .build();
        commentRepository.save(comment);

        // 댓글 수 증가
        post.setCommentCount(post.getCommentCount() + 1);
        postRepository.save(post);
    }

    //댓글 삭제
    public void deleteComment(Long commentId) {
        PostComment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        Post post = comment.getPost();

        // 댓글 삭제
        commentRepository.deleteById(commentId);

        // 댓글 수 감소
        int currentCount = post.getCommentCount();
        post.setCommentCount(Math.max(0, currentCount - 1)); // 음수 방지
        postRepository.save(post);
    }

    //모든댓글 조회
    public List<PostCommentResponse> getCommentsByPostId(Long postId) {
        List<PostComment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> PostCommentResponse.builder()
                        .commentId(comment.getId())
                        .content(comment.getContent())
                        .updatedAt(comment.getUpdatedAt())
                        .userEmail(comment.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());
    }
}
