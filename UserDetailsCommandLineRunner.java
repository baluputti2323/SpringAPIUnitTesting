package Sping8.hours.com.example.SpringAPIUnit.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDetailsCommandLineRunner implements CommandLineRunner {

    @Autowired
    public UserDetailsCommandLineRunner(UserDetailsRepository userDetailsRepository) {
        super();
        this.userDetailsRepository = userDetailsRepository;
    }
    private Logger logger= LoggerFactory.getLogger(getClass());
    private UserDetailsRepository userDetailsRepository;

    @Override
    public void run(String... args) throws Exception {
          userDetailsRepository.save(new UserDetails("Balu","Associate"));
        userDetailsRepository.save(new UserDetails("Bala","Associate"));
        userDetailsRepository.save(new UserDetails("Kishore","Non-User"));
       // List<UserDetails> users= userDetailsRepository.findAll();
        List<UserDetails> users= userDetailsRepository.findByRole("Associate");
                users.forEach(user ->logger.info(String.valueOf(user)));
    }
}
