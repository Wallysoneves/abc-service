package br.com.abc.domain.dto;

public record UserDTO(Long id, String email, String password, String login, String type, String name) {
}
