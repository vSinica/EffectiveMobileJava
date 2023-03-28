package ru.vados.effectivemobile.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vados.effectivemobile.Entity.UserEntity;
import ru.vados.effectivemobile.Repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByName(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user.get(),
                userRepository);

    }
}
