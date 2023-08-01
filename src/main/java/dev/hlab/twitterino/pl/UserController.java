package dev.hlab.twitterino.pl;

import dev.hlab.twitterino.bll.service.UserService;
import dev.hlab.twitterino.dal.dto.*;
import dev.hlab.twitterino.dal.exception.FieldNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usr")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("new")
    public ResponseEntity<Void> signIn(@RequestBody UserSigninDto userSigninDto) {
        try {
            userService.signin(userSigninDto);
            return ResponseEntity.ok().build();
        } catch (FieldNotValidException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            return ResponseEntity.ok(userService.login(userLoginRequestDto));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("get-by-handle")
    public ResponseEntity<GetUserResponseDto> getUser(@RequestBody GetUserByHanldeDto getUserByHanldeDto) {
        try {
            return ResponseEntity.ok(userService.getByHandle(getUserByHanldeDto));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("change-psw")
    public ResponseEntity<Void> changePassword(@RequestBody EditPasswordDTO editPasswordDTO) {
        try {
            userService.changePassword(editPasswordDTO);
            return ResponseEntity.ok().build();
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
