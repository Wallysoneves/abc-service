package br.com.abc.repository;

import br.com.abc.domain.HeaderEntity;
import br.com.abc.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderRepository extends JpaRepository<HeaderEntity, Long> {


    List<HeaderEntity> findByUser(UserEntity user);
}
