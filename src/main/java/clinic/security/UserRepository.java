package clinic.security;

import clinic.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query("SELECT a FROM AppUser a WHERE a.email = ?1 OR a.username = ?1")
    Optional<AppUser> findByEmailOrUsername(String emailOrUsername);
}