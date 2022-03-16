package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserAdminDto;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

import java.util.List;

/**
 * Admin rest controller
 *
 * @author Bartosz Średziński
 * created on 16.03.2022
 */
@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/api/v1/admin/")
@AllArgsConstructor
@Slf4j
public class AdminController{

    private final UserService userService;

    @GetMapping("users")
    public List<UserAdminDto> getUsersDataForAdmin(){
        log.info("GET admin/users");
        return userService.getUserDataAdmin();
    }
}
