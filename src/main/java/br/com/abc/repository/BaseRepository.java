package br.com.abc.repository;

import br.com.abc.core.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

    List<T> findAllByDeletedFalse();

    Page<T> findAllByDeletedFalse(Pageable pageable);

    Optional<T> findByIdAndDeletedFalse(ID id);

    void softDeleteById(ID id);

    void softDelete(T entity);
}
