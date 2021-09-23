package ru.alexkrasnova.spring.lesson11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexkrasnova.spring.lesson11.model.User;
import ru.alexkrasnova.spring.lesson11.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal, Authentication authentication) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }

    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }
}
