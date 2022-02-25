package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

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
}
