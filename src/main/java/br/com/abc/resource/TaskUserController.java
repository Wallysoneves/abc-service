package br.com.abc.resource;

import br.com.abc.domain.TaskUserEntity;
import br.com.abc.domain.UserEntity;
import br.com.abc.service.TaskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskUserController {

    @Autowired
    private TaskUserService taskUserService;

    public ResponseEntity<TaskUserEntity> create(@RequestParam("task") String task, @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(taskUserService.create(task, userId), HttpStatus.CREATED);
    }

    public ResponseEntity<List<TaskUserEntity>> getAll() {
        return new ResponseEntity<>(taskUserService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<TaskUserEntity> getById(@RequestParam("taskId") Long taskId) {
        return new ResponseEntity<>(taskUserService.getById(taskId), HttpStatus.OK);
    }

    public ResponseEntity<TaskUserEntity> update(@RequestBody TaskUserEntity taskUpdate) {
        return new ResponseEntity<>(taskUserService.update(taskUpdate), HttpStatus.OK);
    }

    public void delete(@RequestParam("taskId") Long taskId) {
        taskUserService.delete(taskId);
    }
}
