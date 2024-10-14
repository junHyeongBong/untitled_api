package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.unknownproject.UnknownProjectApplication;
import org.test.unknownproject.test.api.repository.UserRepository;
import org.test.unknownproject.test.entity.User;

@SpringBootTest(classes = UnknownProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class userTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void jpaSave() {
        for(long i = 0L; i<10; i++){
            User user = User.builder().name("Hi"+i).build();
            userRepository.save(user);
        }
    }

    





}
