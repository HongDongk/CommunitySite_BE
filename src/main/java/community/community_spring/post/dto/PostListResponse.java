package community.community_spring.post.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponse {
    private String title;
    private LocalDateTime updatedAt;
    private int likes;
    private int commentCount;
    private int views;
    private String userEmail;
}
