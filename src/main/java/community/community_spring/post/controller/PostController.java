package community.community_spring.post.controller;

import community.community_spring.post.dto.PostCreateRequest;
import community.community_spring.post.dto.PostListResponse;
import community.community_spring.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping("/posts")
    public ResponseEntity<String> createPost(@RequestBody PostCreateRequest request) {
        try {
            postService.createPost(request);
            return ResponseEntity.ok("게시글이 성공적으로 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 등록 실패: " + e.getMessage());
        }
    }

    // 게시글 전체조회
    @GetMapping("/posts")
    public ResponseEntity<List<PostListResponse>> getAllPosts() {
        List<PostListResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
