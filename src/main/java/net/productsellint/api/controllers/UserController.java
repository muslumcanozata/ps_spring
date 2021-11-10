package net.productsellint.api.controllers;

import net.productsellint.business.abstracts.UserService;
import net.productsellint.core.utilities.results.Result;
import net.productsellint.core.utilities.results.SuccessResult;
import net.productsellint.dataTransferObjects.concretes.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addStock")
    ResponseEntity add(@RequestBody UserRequest userRequest) {
        this.userService.save(userRequest);
        return ResponseEntity.status(200).body("Kullanıcı eklendi.");
    }

    @GetMapping("/deleteStock")
    ResponseEntity delete(@RequestParam Integer id) {
        this.userService.delete(id);
        return ResponseEntity.status(200).body("Kullanıcı silindi.");

    }

    @GetMapping("/activateStock")
    ResponseEntity activateStock(@RequestParam Integer id) {
        this.userService.activate(id);
        return ResponseEntity.status(200).body(new SuccessResult("Kullanıcı aktif."));
    }

    @GetMapping("/disableStock")
    ResponseEntity<Result> disable(@RequestParam Integer id) {
        this.userService.disable(id);
        return ResponseEntity.status(200).body(new SuccessResult("Kullanıcı devre dışı."));
    }
}
