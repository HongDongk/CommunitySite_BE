package community.community_spring.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentResponse {
    private Long commentId;
    private String content;
    private LocalDateTime updatedAt;
    private String userEmail;
}
