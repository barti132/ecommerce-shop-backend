package pl.bartoszsredzinski.ecommerceshopv1.controller.v1;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.bartoszsredzinski.ecommerceshopv1.dto.UserAdminDto;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.ProductRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.StockRequest;
import pl.bartoszsredzinski.ecommerceshopv1.dto.request.UserStatusRequest;
import pl.bartoszsredzinski.ecommerceshopv1.model.Stock;
import pl.bartoszsredzinski.ecommerceshopv1.service.ImageService;
import pl.bartoszsredzinski.ecommerceshopv1.service.StockService;
import pl.bartoszsredzinski.ecommerceshopv1.service.UserService;

import javax.validation.Valid;
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
    private final StockService stockService;
    private final ImageService imageService;

    @GetMapping("users")
    public List<UserAdminDto> getUsersDataForAdmin(){
        log.info("GET admin/users");
        return userService.getUserDataAdmin();
    }

    @PutMapping("users/{id}/change-status")
    public void changeUserStatus(@PathVariable Long id, @Valid @RequestBody UserStatusRequest userStatusRequest){
        log.info("PUT admin/users/" + id + "/change-status");
        userService.changeUserStatus(userStatusRequest);
    }

    @GetMapping("stock")
    public List<Stock> getShopStock(){
        log.info("GET admin/stock");
        return stockService.getWholeStock();
    }

    @PutMapping("stock/{id}")
    public void updateStock(@PathVariable Long id, @Valid @RequestBody StockRequest stockRequest){
        log.info("PUT admin/stock/" + id);
        stockService.updateStock(stockRequest);
    }

    @DeleteMapping("stock/{id}")
    public void deleteStock(@PathVariable Long id){
        log.info("DELETE admin/stock/" + id);
        stockService.deleteStock(id);
    }

    @PostMapping("image/upload")
    public void uploadImage(@RequestParam(value = "image") MultipartFile image){
        log.info("POST admin/image/upload");
        imageService.saveImage(image);
    }

    @PostMapping("products/new")
    public void createNewProduct(@Valid @RequestBody ProductRequest productRequest){
        log.info("POST admin/products/new");

    }
}
