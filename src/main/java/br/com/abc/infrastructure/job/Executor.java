package br.com.abc.infrastructure.job;

import br.com.abc.domain.dto.AuthenticationDTO;
import br.com.abc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Executor {

    @Autowired
    private AuthenticationService authenticationService;

    @Scheduled(fixedRate = 600000)
    public void realizarConsulta() {

        System.out.println("#### REALIZANDO CONSULTA");
        this.authenticationService.login(new AuthenticationDTO("teste", "123"));
        System.out.println("#### FIM DA CONSULTA");

    }
}
