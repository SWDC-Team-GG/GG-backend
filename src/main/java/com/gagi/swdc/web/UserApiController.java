package com.gagi.swdc.web;

import com.gagi.swdc.domain.user.User;
import com.gagi.swdc.service.UserService;
import com.gagi.swdc.service.sha256;
import com.gagi.swdc.web.dto.LevelDto;
import com.gagi.swdc.web.dto.LoginDto;
import com.gagi.swdc.web.dto.SignInDto;
import com.gagi.swdc.web.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final sha256 sha256;

    @GetMapping("/")
    public ResponseEntity<UserInfoDto> index(HttpServletRequest request) {
        UserInfoDto info = getSessionUser(request);
        if (info != null) return ResponseEntity.ok(info);
        else return ResponseEntity.badRequest().build();
    }

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
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        if (loginDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if (loginDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");

        try {
            loginDto.setPassword(sha256.encrypt(loginDto.getPassword()));
        } catch (NoSuchAlgorithmException e) {}

        try {
            Pair<User, Boolean> result = userService.login(loginDto);
            if (result.getSecond()) {
                User user = result.getFirst();

                try {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.getId());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                return ResponseEntity.ok("로그인 성공");
            }
            if (!result.getSecond()) return ResponseEntity.badRequest().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("error");
        }
        throw new IllegalArgumentException();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            return ResponseEntity.ok("로그아웃 성공");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/level")
    public ResponseEntity<LevelDto> level(HttpServletRequest request) {
        UserInfoDto info = getSessionUser(request);
        if (info != null) {
            LevelDto level = LevelDto.builder()
                    .scienceLevel(info.getScienceLevel())
                    .humanitiesLevel(info.getHumanitiesLevel())
                    .build();

            return ResponseEntity.ok(level);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/level")
    public ResponseEntity<String> updateLevel(@RequestBody LevelDto levelDto, HttpServletRequest request) {
        UserInfoDto info = getSessionUser(request);
        if (info != null) {
            try {
                userService.updateLevel(info.getId(), levelDto);
                return ResponseEntity.ok("업데이트 완료");
            } catch (IllegalArgumentException e) {}
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    public UserInfoDto getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long id = (Long) session.getAttribute("user");
            if (id != null) {
                User user = userService.select(id);

                UserInfoDto userInfo = UserInfoDto.builder()
                        .id(user.getId())
                        .userId(user.getUserId())
                        .name(user.getName())
                        .scienceLevel(user.getScienceLevel())
                        .humanitiesLevel(user.getHumanitiesLevel())
                        .build();

                return userInfo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
