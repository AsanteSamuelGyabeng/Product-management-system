package ecommerce.products.controllers;

import ecommerce.products.exceptions.ResourceNotFoundException;
import ecommerce.products.models.User;
import ecommerce.products.repository.UserRepository;
import ecommerce.products.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User saveuser = userService.addUser(user);
        return ResponseEntity.ok(saveuser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id)throws ResourceNotFoundException{
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5" ) int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String direction) throws ResourceNotFoundException {
       Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(direction),sortBy));
        Page<User> users = userService.allusers(pageable);
       return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) throws ResourceNotFoundException {
        User updatedUser = userService.updateUserDetails(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws ResourceNotFoundException{
        boolean deletedUser = userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}