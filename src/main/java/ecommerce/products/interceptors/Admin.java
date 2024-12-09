package ecommerce.products.interceptors;

import ecommerce.products.models.User;
import ecommerce.products.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class Admin {
    Logger log = (Logger) LoggerFactory.getLogger(Admin.class);
    UserRepository userRepository;

    @Autowired
    public Admin(UserRepository userRepository) {
     this.userRepository = userRepository;
    }

    @GetMapping("/check")
    public void CheckAdmin() {
        Optional<User> admin = userRepository.findByUsername("kayone");
        if(!admin.isPresent()) {
            log.info("he is in");
        }else{
            log.info("admin is in");
        }
    }


}
