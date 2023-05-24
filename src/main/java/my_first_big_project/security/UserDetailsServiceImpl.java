package my_first_big_project.security;

import lombok.RequiredArgsConstructor;
import my_first_big_project.entity.User;
import my_first_big_project.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username).orElseThrow(() -> {
                    throw new UsernameNotFoundException("Пользователь не найден");
                }
        );
        return new UserDetailsImpl(user);
    }
}