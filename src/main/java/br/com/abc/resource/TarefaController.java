package br.com.abc.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    @GetMapping("/todas")
    public ResponseEntity<String> buscarTodasTarefas() {
        return new ResponseEntity<>("Todas as tarefas", HttpStatus.OK);
    }
}
