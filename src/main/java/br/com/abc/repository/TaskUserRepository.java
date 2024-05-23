package br.com.abc.repository;

import br.com.abc.domain.TaskUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskUserRepository extends JpaRepository<TaskUserEntity, Long> {
}
