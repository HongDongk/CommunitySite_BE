package community.community_spring;

import community.community_spring.repository.JdbcTemplateMemberRepository;
import community.community_spring.repository.JpaMemberRepository;
import community.community_spring.repository.MemberRepository;
import community.community_spring.repository.MemoryMemberRepository;
import community.community_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    private EntityManager em;
    //
    //    @Autowired
    //    public SpringConfig(EntityManager em) {
    //        this.em = em;
    //    }

    @Bean
    public MemberService memberService(){
        // return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    //    @Bean
    //    public MemberRepository memberRepository(){
    //        // return new MemoryMemberRepository();
    //        // return new JdbcTemplateMemberRepository(dataSource);
    //        return new JpaMemberRepository(em);
    //
    //    }

}
