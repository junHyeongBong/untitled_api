package test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.unknownproject.UnknownProjectApplication;
import org.test.unknownproject.test.api.repository.UserRepository;
import org.test.unknownproject.test.entity.User;

import java.util.Optional;

@SpringBootTest(classes = UnknownProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void jpaSave() {
        for(long i = 0L; i<10; i++){
            User user = User.builder().name("Hi"+i).build();
            userRepository.save(user);
        }
    }

    @Test
    void jpaFind() {
        Optional<User> user = userRepository.findById(1L);
        System.out.println(user.isPresent() ? user.get().toString() : "Nothing");
    }

    @Test
    void jpaDelete() {
        userRepository.delete(User.builder().name("Hi4").id(5L).build());
    }


}
