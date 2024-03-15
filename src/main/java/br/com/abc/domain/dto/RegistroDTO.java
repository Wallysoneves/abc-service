package br.com.abc.domain.dto;

import br.com.abc.domain.enums.TipoUsuario;
import br.com.abc.domain.enums.UsuarioRole;

public record RegistroDTO(String login, String senha, UsuarioRole role, String nome, String email, TipoUsuario tipo) {
}
