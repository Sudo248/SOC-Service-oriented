package com.sudo248.userservice.controller;

import com.sudo248.domain.base.BaseResponse;
import com.sudo248.domain.common.Constants;
import com.sudo248.domain.util.Utils;
import com.sudo248.userservice.controller.dto.UserDto;
import com.sudo248.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.HeaderParam;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getUser(
            @RequestHeader(Constants.HEADER_USER_ID) String userId
    ) {
        return Utils.handleException(() -> {
            UserDto userDto = userService.getUser(userId);
            return BaseResponse.ok(userDto);
        });
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<?>> getOtherUser(
            @PathVariable("userId") String userId
    ) {
        return Utils.handleException(() -> {
            UserDto userDto = userService.getUser(userId);
            return BaseResponse.ok(userDto);
        });
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> postUser(
            @RequestBody UserDto userDto
    ) {
        return Utils.handleException(() -> {
            UserDto _userDto = userService.postUser(userDto);
            return BaseResponse.ok(_userDto);
        });
    }

    @PutMapping
    public ResponseEntity<BaseResponse<?>> putUser(
            @RequestHeader(Constants.HEADER_USER_ID) String userId,
            @RequestBody UserDto userDto
    ) {
        return Utils.handleException(() -> {
            UserDto _userDto = userService.putUser(userId, userDto);
            return BaseResponse.ok(_userDto);
        });
    }
}
