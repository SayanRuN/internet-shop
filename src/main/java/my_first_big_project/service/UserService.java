package my_first_big_project.service;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.User;
import my_first_big_project.enumuration.Role;;
import my_first_big_project.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return userRepository.findUserByLogin(authentication.getName()).orElse(null);
    }

    public User registration(String login, String password, String email,
                             String number, String name, String surName,
                             LocalDateTime regDate) {
        User user = new User(login, email, number, name, surName, regDate);
        user.setRegDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        userRepository.save(user);
        return  user;
    }
}
