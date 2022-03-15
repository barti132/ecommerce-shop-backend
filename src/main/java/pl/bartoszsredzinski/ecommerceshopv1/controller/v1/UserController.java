package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszsredzinski.ecommerceshopv1.dto.InvoiceDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.service.InvoiceService;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * User rest controller
 *
 * @author Bartosz Średziński
 * created on 24.02.2022
 */
@RestController
@RequestMapping("/api/v1/user/{login}")
@AllArgsConstructor
@Slf4j
public class UserController{

    private final UserService userService;

    @GetMapping()
    public UserDto getCurrentUser(@PathVariable String login){
        log.info("GET user/" + login);
        return userService.getCurrentUserDto(login);
    }

    @PostMapping("/add-address")
    public ResponseEntity<String> addAddressToCurrentUser(@PathVariable String login, @Valid @RequestBody Address address){
        log.info("POST user/" + login + "/add-address");
        userService.addAddressToCurrentUser(login, address);
        return new ResponseEntity<>("Adding address success", HttpStatus.OK);
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddressByIdFromCurrentUser(@PathVariable String login, @PathVariable Long id){
        log.info("DELETE user/" + login + "/address/" + id);
        userService.deleteAddressFromCurrentUser(login, id);
    }

    @PutMapping("/update")
    public UserDto updateCurrentUser(@PathVariable String login, @Valid @RequestBody UserDto userDto){
        log.info("Put user/" + login + "/update");
        return userService.updateCurrentUser(login, userDto);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changeUserPassword(@PathVariable String login, @Valid @RequestBody PasswordDto passwordDto){
        log.info("PUT user/" + login + "/change-password");
        userService.changePassword(login, passwordDto);
        return new ResponseEntity<>("Changing password success", HttpStatus.OK);
    }
}
