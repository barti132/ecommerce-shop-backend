package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.bartoszsredzinski.ecommerceshopv1.dto.PasswordDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserDto;
import pl.bartoszsredzinski.ecommerceshopv1.model.Address;
import pl.bartoszsredzinski.ecommerceshopv1.service.InvoiceService;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * User controller test
 *
 * @author Bartosz Średziński
 * created on 05.03.2022
 */
class UserControllerTest{

    private final UserDto userDto = new UserDto("email", "login", "name", "lastName", "12321", new ArrayList<>());
    private final UserService userService = Mockito.mock(UserService.class);
    private final UserController userController = new UserController(userService);

    @Test
    public void getCurrentUser_should_return_UserDto(){
        when(userService.getCurrentUserDto("login")).thenReturn(userDto);

        assertNotNull(userController.getCurrentUser("login"));
    }

    @Test
    public void addAddressToCurrentUser_should_return_responseEntity(){
        assertEquals("Adding address success", userController.addAddressToCurrentUser("login", new Address()).getBody());
    }

    @Test
    public void updateCurrentUser_should_return_userDto(){
        when(userService.updateCurrentUser("login", userDto)).thenReturn(userDto);
        assertEquals(userDto, userController.updateCurrentUser("login", userDto));
    }

    @Test
    public void changeUserPassword_should_return_responseEntity(){
        assertEquals("Changing password success", userController.changeUserPassword("login", new PasswordDto()).getBody());
    }
}