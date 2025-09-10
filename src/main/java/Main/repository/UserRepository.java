package Main.repository;

import Main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User findByEmail(String email);

    @Query("SELECT u " +
            "FROM User u " +
            "WHERE (:email IS NULL OR u.email LIKE CONCAT('%', :email, '%'))")
    List<User> findByEmailContaining(@Param("email") String email);

}
