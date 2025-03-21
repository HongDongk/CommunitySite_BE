package community.community_spring.service;

import community.community_spring.domain.Member;
import community.community_spring.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    // @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        userRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        userRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return userRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return userRepository.findById(memberId);
    }
}
