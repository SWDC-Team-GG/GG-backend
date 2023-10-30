package com.gagi.swdc.web;

import com.gagi.swdc.service.UserService;
import com.gagi.swdc.service.sha256;
import com.gagi.swdc.web.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final sha256 sha256;

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) {
        if(signInDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if(signInDto.getName().isEmpty()) return ResponseEntity.badRequest().body("이름이 비어있습니다.");
        if(signInDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");
        if(signInDto.getUserId().length() > 25) return ResponseEntity.badRequest().body("25글자를 넘으면 안됩니다.");
        if(signInDto.getName().length() > 10) return ResponseEntity.badRequest().body("10글자를 넘으면 안됩니다.");
        try {
            try {
                signInDto.setPassword(sha256.encrypt(signInDto.getPassword()));
            } catch (NoSuchAlgorithmException e) {}
            userService.signIn(signInDto);
            return ResponseEntity.ok().body("회원가입 성공");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("error");
        }
    }
}
