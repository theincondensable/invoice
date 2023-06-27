package io.incondensable.auth.repository;

import io.incondensable.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author abbas
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from t_user u where u.username = :username")
    @Transactional(readOnly = true)
    Optional<User> findByUsername(@Param("username") String username);

}
