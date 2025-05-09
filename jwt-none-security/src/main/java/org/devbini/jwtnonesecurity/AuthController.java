package org.devbini.jwtnonesecurity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String id) {
        return JwtUtil.generateToken(id);
    }

    @GetMapping("/check")
    public String hello(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return JwtUtil.getId(token);
    }
}
