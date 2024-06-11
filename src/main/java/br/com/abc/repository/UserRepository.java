package br.com.abc.repository;

import br.com.abc.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByLogin(String login);

    UserEntity findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE LOGIN = :LOGIN")
    UserEntity encontrarPorLogin(@Param("LOGIN") String login);


}