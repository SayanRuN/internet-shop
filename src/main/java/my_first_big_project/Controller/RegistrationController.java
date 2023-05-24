package my_first_big_project.Controller;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.User;
import my_first_big_project.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/registration")
@RequiredArgsConstructor
public class RegistrationController {
   private final UserService userService;

    @PostMapping(path = "/reg")
    public ModelAndView registration(
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surName,
            @RequestParam(required = false) LocalDateTime regDate
    ) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.registration(login, password, email, number, name, surName, regDate);
        modelAndView.setViewName("Authorization");
        return modelAndView;
    }

    @GetMapping(path = "/reg")
    public ModelAndView registrationView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Registration");
        return modelAndView;
    }

    @GetMapping(path = "/login")
    public ModelAndView authorization() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Authorization");
        return modelAndView;
    }
}