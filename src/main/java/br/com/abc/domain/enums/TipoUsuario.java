package br.com.abc.domain.enums;

public enum TipoUsuario {

    PROFESSOR("professor"),
    ALUNO("aluno");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
