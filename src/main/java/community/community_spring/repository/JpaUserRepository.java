package community.community_spring.repository;

import community.community_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<Member, Long>, UserRepository {
    @Override
    Optional<Member> findByName(String name);
}
