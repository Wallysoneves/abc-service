package br.com.abc.repository;

import br.com.abc.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    @Override
    default Optional<UserEntity> findByIdAndDeletedFalse(Long id) {
        return findById(id).filter(user -> !user.getDeleted());
    }

    @Override
    default void softDeleteById(Long id) {
        findById(id).ifPresent(user -> {
            user.setDeleted(true);
            save(user);
        });
    }

    @Override
    default void softDelete(UserEntity user) {
        user.setDeleted(true);
        save(user);
    }

    @Query("SELECT u FROM TaskUserEntity u WHERE u.user = ?1 AND u.deleted = false")
    UserEntity findByLoginAndNotDeleted(String login);
}
