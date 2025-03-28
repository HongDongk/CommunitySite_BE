package community.community_spring.post.service;

import community.community_spring.post.domain.Post;
import community.community_spring.post.dto.PostCreateRequest;
import community.community_spring.post.dto.PostDetailResponse;
import community.community_spring.post.dto.PostListResponse;
import community.community_spring.post.dto.PostUpdateRequest;
import community.community_spring.post.repository.JpaPostRepository;
import community.community_spring.user.domain.User;
import community.community_spring.user.repository.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final JpaPostRepository postRepository;
    private final JpaUserRepository userRepository;

    @Autowired
    public PostService(JpaPostRepository postRepository, JpaUserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 게시글 생성
    public void createPost(PostCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("작성자(user_id)가 존재하지 않습니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(request.getImageUrl())
                .user(user)
                .likes(0)
                .views(0)
                .commentCount(0)
                .build();

        postRepository.save(post);
    }

    // 게시글 전체조회
    public List<PostListResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostListResponse.builder()
                        .title(post.getTitle())
                        .updatedAt(post.getUpdatedAt())
                        .likes(post.getLikes())
                        .commentCount(post.getCommentCount())
                        .views(post.getViews())
                        .userEmail(post.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    // 게시글 상세조회
    public PostDetailResponse getPostDetail(Long postId) {
        return postRepository.findById(postId)
                .map(post -> PostDetailResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .imageUrl(post.getImageUrl())
                        .updatedAt(post.getUpdatedAt())
                        .likes(post.getLikes())
                        .commentCount(post.getCommentCount())
                        .views(post.getViews())
                        .userEmail(post.getUser().getEmail())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
    }

    //게시글 상세수정
    public void updatePost(Long postId, PostUpdateRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrl(request.getImageUrl());
        post.setUpdatedAt(LocalDateTime.now());

        postRepository.save(post);
    }

    //게시글 상세삭제
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        postRepository.deleteById(postId);
    }
}
