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
import org.springframework.web.bind.annotation.*;

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

    // 회원 가입
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDto signInDto) throws NoSuchAlgorithmException {
        // 예외 처리
        if(signInDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if(signInDto.getName().isEmpty()) return ResponseEntity.badRequest().body("이름이 비어있습니다.");
        if(signInDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");
        if(signInDto.getUserId().length() > 25) return ResponseEntity.badRequest().body("유저 아이디가 25글자를 넘으면 안됩니다.");
        if(signInDto.getName().length() > 10) return ResponseEntity.badRequest().body("이름이 10글자를 넘으면 안됩니다.");
        if(userService.checkUserId(signInDto.getUserId())) return ResponseEntity.badRequest().body("같은 아이디가 있습니다.");

        try {
            // 비밀번호 암호화
            signInDto.setPassword(sha256.encrypt(signInDto.getPassword()));

            // 회원 생성
            userService.signIn(signInDto);
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().build();
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        // 예외 처리
        if (loginDto.getUserId().isEmpty()) return ResponseEntity.badRequest().body("아이디가 비어있습니다.");
        if (loginDto.getPassword().isEmpty()) return ResponseEntity.badRequest().body("비밀번호가 비어있습니다.");

        // 비밀번호 암호화
        try {
            loginDto.setPassword(sha256.encrypt(loginDto.getPassword()));
        } catch (NoSuchAlgorithmException e) {}

        try {
            // 회원 조회
            Pair<User, Boolean> result = userService.login(loginDto);
            if (result.getSecond()) {
                User user = result.getFirst();

                // 세션에 회원 저장
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

    // 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 세션 조회
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션 삭제
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
                    .education(info.getEducation())
                    .field(info.getField())
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
                        .education(user.getEducation())
                        .field(user.getField())
                        .survey(user.getSurvey())
                        .build();

                return userInfo;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/survey")
    public boolean updateSurvey(HttpServletRequest request) {
        UserInfoDto info = getSessionUser(request);
        if (info != null && !info.getSurvey()) {
            userService.updateSurvey(info.getId());
            return false;
        } else return true;
    }
}
