package br.com.abc.repository;

import br.com.abc.domain.HeaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<HeaderEntity, Long> {

}
