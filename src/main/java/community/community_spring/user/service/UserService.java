package community.community_spring.user.service;

import community.community_spring.user.domain.User;
import community.community_spring.user.dto.UserSignupRequest;
import community.community_spring.user.repository.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UserService {
    private final JpaUserRepository userRepository;

    @Autowired
    public UserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserSignupRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setProfileUrl(request.getProfileUrl());

        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다.");
        }

        userRepository.deleteById(userId);
    }
}
