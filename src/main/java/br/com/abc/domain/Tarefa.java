package br.com.abc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Lob // Usado para armazenar grandes objetos binários (BLOBs) no banco de dados
    @Column(name = "conteudo", columnDefinition = "BYTEA")
    private byte[] conteudo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "tarefa")
    private List<Versao> versoes;

    @Column(name = "status")
    private String status;

    @Column(name = "geracao")
    private LocalDateTime criadoEm;
}
