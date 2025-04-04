package community.community_spring.user.dto;

import lombok.*;

@Setter
@Getter
public class UserSignupRequest {
    // Getter, Setter
    private String email;
    private String password;
    private String nickname;
    private String profileUrl;
}
