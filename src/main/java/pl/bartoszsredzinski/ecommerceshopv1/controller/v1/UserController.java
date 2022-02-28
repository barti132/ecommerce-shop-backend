package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;
import pl.bartoszsredzinski.ecommerceshopv1.service.auth.AuthService;

/**
 * User rest controller
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Slf4j
public class UserController{

    private final UserService userService;

    /**
     * @param id user id
     * @return UserDto user with given id
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        log.info("GET users/" + id);
        return userService.getUserByID(id);
    }

    @GetMapping("/currentUser")
    public UserDto getCurrentUser(){
        log.info("GET users/currentUser");
        return userService.getCurrentUser();
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddressToCurrentUser(@RequestBody Address address){
        log.info("POST users/addAddressToCurrentUser");
        userService.addAddressToCurrentUser(address);
        return new ResponseEntity<>("Adding address success", HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddressByIdFromCurrentUser(@PathVariable Integer id){
        log.info("DELETE users/address/" + id);
        userService.deleteAddressFromCurrentUser(id);
    }
}
