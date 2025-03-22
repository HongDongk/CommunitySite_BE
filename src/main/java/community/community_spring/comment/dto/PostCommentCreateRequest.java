package community.community_spring.comment.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentCreateRequest {
    private Long userId;
    private String content;
}
