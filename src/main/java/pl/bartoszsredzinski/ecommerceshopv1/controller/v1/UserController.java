package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

/**
 * User rest controller
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class UserController{

    private final UserService userService;

    @GetMapping("/currentUser")
    public UserDto getCurrentUser(){
        log.info("GET user/currentUser");
        return userService.getCurrentUserDto();
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddressToCurrentUser(@RequestBody Address address){
        log.info("POST user/addAddressToCurrentUser");
        userService.addAddressToCurrentUser(address);
        return new ResponseEntity<>("Adding address success", HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddressByIdFromCurrentUser(@PathVariable Integer id){
        log.info("DELETE user/address/" + id);
        userService.deleteAddressFromCurrentUser(id);
    }

    @PutMapping("/update")
    public UserDto updateCurrentUser(@RequestBody UserDto userDto){
        log.info("Put user/update");
        return userService.updateCurrentUser(userDto);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody PasswordDto passwordDto){
        log.info("PUT user/changePassword");
        userService.changePassword(passwordDto);
        return new ResponseEntity<>("Changing password success", HttpStatus.OK);
    }
}
