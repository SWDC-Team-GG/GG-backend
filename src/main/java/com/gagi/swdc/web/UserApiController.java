package com.gagi.swdc.web;

import com.gagi.swdc.service.UserService;
import com.gagi.swdc.service.sha256;
import com.gagi.swdc.web.dto.LoginDto;
import com.gagi.swdc.web.dto.SignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
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
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) throws NoSuchAlgorithmException {
        if(signInDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if(signInDto.getName().isEmpty()) return ResponseEntity.badRequest().body("이름이 비어있습니다.");
        if(signInDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");
        if(signInDto.getUserId().length() > 25) return ResponseEntity.badRequest().body("유저 아이디가 25글자를 넘으면 안됩니다.");
        if(signInDto.getName().length() > 10) return ResponseEntity.badRequest().body("이름이 10글자를 넘으면 안됩니다.");
        try {
            signInDto.setPassword(sha256.encrypt(signInDto.getPassword()));
            userService.signIn(signInDto);
            return ResponseEntity.ok().body("회원가입 성공");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) throws NoSuchAlgorithmException {
        if(loginDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if(loginDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");
        loginDto.setPassword(sha256.encrypt(loginDto.getPassword()));
        try {
            Pair<String, Boolean> result = userService.login(loginDto);
            if(result.getSecond()) return ResponseEntity.ok().body(result.getFirst());
            if(!result.getSecond()) return ResponseEntity.badRequest().body(result.getFirst());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("error");
        }
        throw new IllegalArgumentException();
    }
}
