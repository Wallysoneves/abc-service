package br.com.abc.service;

import br.com.abc.entity.UserEntity;
import br.com.abc.domain.dto.AuthenticationDTO;
import br.com.abc.domain.dto.LoginDTO;
import br.com.abc.domain.dto.RegisterDTO;
import br.com.abc.repository.UserRepository;
import br.com.abc.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, TokenService tokenService, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLoginAndNotDeleted(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
        return user;
    }

    public LoginDTO login(@Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        if (!auth.isAuthenticated()) {
            throw new SecurityException("Authentication failed");
        }

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return new LoginDTO(token);
    }

    public void register(@Valid RegisterDTO data) {
        if (userRepository.findByLoginAndNotDeleted(data.login()) != null) {
            throw new RuntimeException("This user is already registered!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = UserEntity.builder()
                .login(data.login())
                .password(encryptedPassword)
                .role(data.role())
                .name(data.name())
                .email(data.email())
                .type(data.type())
                .build();

        userRepository.save(newUser);
    }
}
