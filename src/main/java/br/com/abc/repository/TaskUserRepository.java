package br.com.abc.repository;

import br.com.abc.domain.TaskUserEntity;
import br.com.abc.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUserEntity, Long> {

    List<TaskUserEntity> findByUser(UserEntity user);
}
