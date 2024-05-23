package br.com.abc.resource;

import br.com.abc.domain.HeaderEntity;
import br.com.abc.service.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/header")
public class HeaderController {

    @Autowired
    private HeaderService headerService;

    @GetMapping(value = "/id",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<HeaderEntity> getById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(headerService.getById(id), HttpStatus.OK);
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<List<HeaderEntity>> getAll() {
        return new ResponseEntity<>(headerService.getAll(), HttpStatus.OK);
    }

    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<HeaderEntity> create(@RequestBody HeaderEntity headerEntityCreate) {
        return new ResponseEntity<>(headerService.create(headerEntityCreate), HttpStatus.CREATED);
    }

    @PutMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public ResponseEntity<HeaderEntity> update(HeaderEntity headerUpdate) {
        return new ResponseEntity<>(headerService.update(headerUpdate), HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public void delete(@RequestParam Long id) {
        headerService.delete(id);
    }
}
