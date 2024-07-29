package com.shotty.shotty.Controller;

import com.shotty.shotty.dto.EncryptedUserDto;
import com.shotty.shotty.dto.ResisterRequestDto;
import com.shotty.shotty.dto.UserResponseDto;
import com.shotty.shotty.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody ResisterRequestDto resisterRequestDto) {
        EncryptedUserDto encryptedUserDto = EncryptedUserDto.from(resisterRequestDto);
        UserResponseDto userResponseDto = userService.register(encryptedUserDto);

        return ResponseEntity.ok(userResponseDto);
    }

}
