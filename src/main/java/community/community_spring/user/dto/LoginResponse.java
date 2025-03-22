package community.community_spring.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class LoginResponse {
    private Long id;
    private String email;
    private String nickname;
    private String profileUrl;
}

