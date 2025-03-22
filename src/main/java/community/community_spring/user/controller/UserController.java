package community.community_spring.user.controller;

import community.community_spring.user.dto.*;
import community.community_spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping( "/users")
    public ResponseEntity<?> users(@RequestBody UserSignupRequest request){
        try {
            userService.signup(request);
            return ResponseEntity.ok("회원가입 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }

    // 회원삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUserById(userId);
            return ResponseEntity.ok("회원 삭제 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("회원 삭제 실패: " + e.getMessage());
        }
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("로그아웃 완료");
    }

    //회원정보변경(닉네임)
    @PutMapping("/userinfo/{userId}")
    public ResponseEntity<String> updateNickname(
            @PathVariable Long userId,
            @RequestBody UpdateNicknameRequest request
    ) {
        try {
            userService.updateNickname(userId, request.getNickname());
            return ResponseEntity.ok("닉네임 변경 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("닉네임 변경 실패: " + e.getMessage());
        }
    }

    @PutMapping("/users/password/{userId}")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody UpdatePasswordRequest request
    ) {
        try {
            userService.updatePassword(userId, request.getPassword());
            return ResponseEntity.ok("비밀번호 변경 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("비밀번호 변경 실패: " + e.getMessage());
        }
    }
}
