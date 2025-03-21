package community.community_spring;

import community.community_spring.repository.UserRepository;
import community.community_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final UserRepository userRepository;

    @Autowired
    public SpringConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserService memberService(){
        return new UserService(userRepository);
    }
}
