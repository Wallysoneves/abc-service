package br.com.abc.entity;

import br.com.abc.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "headers")
public class HeaderEntity extends BaseEntity {

    // Getters and Setters
    @Column(name = "city")
    private String city;

    @Column(name = "school")
    private String school;

    @Column(name = "year")
    private String year;

    @Column(name = "header_name")
    @Size(max = 255, message = "Header name must not exceed 255 characters")
    private String headerName;

    @Column(name = "subject")
    @NotBlank(message = "Subject cannot be blank")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
