package br.com.abc.resource;

import br.com.abc.domain.UserEntity;
import br.com.abc.domain.dto.AuthenticationDTO;
import br.com.abc.domain.dto.LoginDTO;
import br.com.abc.domain.dto.RegisterDTO;
import br.com.abc.domain.dto.UserDTO;
import br.com.abc.infrastructure.exception.AbcException;
import br.com.abc.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.abc.repository.UserRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data) {

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.geneteToken((UserEntity) auth.getPrincipal());
            UserEntity userEntity = this.userRepository.encontrarPorLogin(data.login());

            return ResponseEntity.ok(new LoginDTO(
                                                    new UserDTO(userEntity.getId()
                                                            , userEntity.getEmail()
                                                            , userEntity.getPassword()
                                                            , userEntity.getLogin()
                                                            , userEntity.getType().getTipo()
                                                            , userEntity.getName())
                                                    , token));
        } catch (Exception e) {
            throw new AbcException("Usuário não possui cadastro", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) {
            return new ResponseEntity<>("Este login já possui vinculo a uma conta", HttpStatus.BAD_REQUEST);
        }

        if (this.userRepository.findByEmail(data.email()) != null) {
            return new ResponseEntity("Este email já é vinculado a uma conta", HttpStatus.BAD_REQUEST);
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

        this.userRepository.save(newUser);

        return ResponseEntity.ok(this.login(new AuthenticationDTO(data.login(), data.password())).getBody());
    }
}
