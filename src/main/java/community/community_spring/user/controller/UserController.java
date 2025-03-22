package community.community_spring.user.controller;


import community.community_spring.user.dto.UserSignupRequest;
import community.community_spring.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
