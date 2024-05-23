package br.com.abc.domain;

import br.com.abc.domain.core.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "task_users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskUserEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull(message = "User must not be null")
    private UserEntity user;

    @Lob
    @Column(name = "html_content", nullable = false)
    @NotBlank(message = "HTML content must not be blank")
    private String htmlContent;

}
