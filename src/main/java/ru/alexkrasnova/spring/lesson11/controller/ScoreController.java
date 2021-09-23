package ru.alexkrasnova.spring.lesson11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexkrasnova.spring.lesson11.model.User;
import ru.alexkrasnova.spring.lesson11.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/score")
public class ScoreController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public String getScoreById(@PathVariable Long id) {
        User user = userService.findById(id);
        return "id: " + id.toString() + ", score: " + user.getScore();
    }

    @GetMapping("/inc")
    public void incrementScore(Principal principal) {
        userService.incrementScoreByUsername(principal.getName());
    }

}
