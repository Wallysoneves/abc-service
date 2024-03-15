package br.com.abc.resource;

import br.com.abc.domain.Usuario;
import br.com.abc.domain.dto.AutenticationDTO;
import br.com.abc.domain.dto.LoginDTO;
import br.com.abc.domain.dto.RegistroDTO;
import br.com.abc.infrastructure.exception.AbcException;
import br.com.abc.repository.UsuarioRepository;
import br.com.abc.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.geneteToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistroDTO data) {
        if(this.usuarioRepository.findByLogin(data.login()) != null) {
            return new ResponseEntity<>("Esse usuário já possui cadastro!", HttpStatus.BAD_REQUEST);
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUser = Usuario.builder()
                .login(data.login())
                .senha(encryptedPassword)
                .role(data.role())
                .build();

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}