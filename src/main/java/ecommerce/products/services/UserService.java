package ecommerce.products.services;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.exceptions.UserAlreadyExistsException;
import ecommerce.products.models.User;
import ecommerce.products.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


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
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
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
            existingUser.setName(user.getName());
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
