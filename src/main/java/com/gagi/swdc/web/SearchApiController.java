package com.gagi.swdc.web;

import com.gagi.swdc.domain.user.User;
import com.gagi.swdc.service.SearchService;
import com.gagi.swdc.service.UserService;
import com.gagi.swdc.web.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final SearchService searchService;
    private final UserService userService;

    @PostMapping("/search")
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody List<SearchDto> searchDto) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long id = (Long) session.getAttribute("user");
            if (id != null) {
                User user = userService.select(id);
                for(SearchDto search : searchDto) {
                    search.setUserId(user.getId());
                    searchService.save(search);
                }
                return ResponseEntity.ok("성공");
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchDto>> select(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long id = (Long) session.getAttribute("user");
            if (id != null) {
                User user = userService.select(id);
                List<SearchDto> data = searchService.select(user.getId());
                return ResponseEntity.ok(data);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
