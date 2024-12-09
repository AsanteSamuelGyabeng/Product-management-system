package ecommerce.products.services;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.exceptions.UserAlreadyExistsException;
import ecommerce.products.models.User;
import ecommerce.products.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    /**
     * @allusers this method gets all users
     * @return
     * @throws ResourceNotFoundException
     */
    public Page<User> allusers(Pageable pageable) throws ResourceNotFoundException{

        Page<User> users = userRepository.findAll(pageable);
        if(!users.isEmpty()){
            return users;
        }else{
            throw new ResourceNotFoundException("No users found");
        }
    }

    /**
     * @getUser this method gets a user by id
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public User getUser(Long id) throws ResourceNotFoundException{
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * @addUser this method adds a user
     * @param user
     * @return
     * @throws UserAlreadyExistsException
     */
    public User addUser(User user){
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()){
            log.info("user already exists");
            throw new UserAlreadyExistsException("user already exists");
        }else{
            return userRepository.save(user);
        }
    }

    /**
     * @updateUserDetails updates user
     * @param id
     * @param user
     * @return
     * @throws ResourceNotFoundException
     */
    public User updateUserDetails(Long id, User user) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User existingUser = userOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return userRepository.save(existingUser);
        }else {
            throw new ResourceNotFoundException("User not found");
        }
    }


    /**
     * @deleteUser deletes user
     * @param id
     * @return
     * @throws ResourceNotFoundException
     */
    public boolean deleteUser(Long id) throws ResourceNotFoundException {
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return true;
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
