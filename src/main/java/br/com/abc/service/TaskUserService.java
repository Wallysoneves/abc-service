package br.com.abc.service;

import br.com.abc.domain.TaskUserEntity;
import br.com.abc.domain.UserEntity;
import br.com.abc.infrastructure.exception.AbcException;
import br.com.abc.repository.TaskUserRepository;
import br.com.abc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskUserService {

    @Autowired
    private TaskUserRepository taskUserRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskUserEntity create(String task, Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AbcException("user not found", HttpStatus.BAD_REQUEST));

        TaskUserEntity taskUser = TaskUserEntity.builder()
                                                .user(user)
                                                .htmlContent(task)
                                                .build();

        return taskUserRepository.save(taskUser);
    }

    public List<TaskUserEntity> getAll() {
        return taskUserRepository.findAll();
    }

    public TaskUserEntity getById(Long taskId) {
        return taskUserRepository.findById(taskId).orElseThrow(() -> new AbcException("user not found", HttpStatus.BAD_REQUEST));
    }

    public List<TaskUserEntity> getByUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new AbcException("user not found", HttpStatus.BAD_REQUEST));

        return taskUserRepository.findByUser(user);
    }

    public TaskUserEntity update(TaskUserEntity taskUpdate) {
        return taskUserRepository.save(taskUpdate);
    }

    public void delete(Long taskId) {
        taskUserRepository.deleteById(taskId);
    }
}
