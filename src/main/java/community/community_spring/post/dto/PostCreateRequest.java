package community.community_spring.post.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequest {
    private String title;
    private String content;
    private String imageUrl;
    private Long userId;
}
