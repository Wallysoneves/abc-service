package br.com.abc.domain;

import br.com.abc.domain.core.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Table(name = "headers")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class HeaderEntity extends BaseEntity {

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
