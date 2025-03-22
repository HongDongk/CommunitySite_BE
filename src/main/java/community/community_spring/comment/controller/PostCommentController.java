package community.community_spring.comment.controller;

import community.community_spring.comment.dto.PostCommentCreateRequest;
import community.community_spring.comment.dto.PostCommentResponse;
import community.community_spring.comment.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostCommentController {

    private final PostCommentService commentService;

    @Autowired
    public PostCommentController(PostCommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/{postId}/comment")
    public ResponseEntity<String> createComment(
            @PathVariable Long postId,
            @RequestBody PostCommentCreateRequest request
    ) {
        try {
            commentService.createComment(postId, request);
            return ResponseEntity.ok("댓글 작성 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("댓글 작성 실패: " + e.getMessage());
        }
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok("댓글 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("댓글 삭제 실패: " + e.getMessage());
        }
    }

    //모든댓글 조회
    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getComments(@PathVariable Long postId) {
        try {
            List<PostCommentResponse> responses = commentService.getCommentsByPostId(postId);
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("댓글 조회 실패: " + e.getMessage());
        }
    }
}
