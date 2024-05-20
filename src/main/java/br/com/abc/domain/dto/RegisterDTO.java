package br.com.abc.domain.dto;

import br.com.abc.domain.enums.TipoUsuario;
import br.com.abc.domain.enums.UsuarioRole;

public record RegisterDTO(String login, String password, UsuarioRole role, String name, String email, TipoUsuario type) {
}
